import java.io.{File, PrintWriter}

import rx.lang.scala.Observable
import spray.json._
import org.scalastuff.json.spray._

import scala.collection._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.io.Source
import scala.sys.process.ProcessLogger
import scala.util.Try

class GitParser(gitroot: String, branch: String, saveFile: Option[String]) {
  private val root = new java.io.File(gitroot)
  private val shaLength = 40
  private lazy val commits = sys.process.Process(Seq("git", "log", branch, "--format=oneline"), root)
    .lineStream
    .map(c => (
      SHA(c.take(shaLength)),
      c.substring(shaLength).trim
    )).toList

  // Files that changed per commit
  def revChanges: Observable[(Int,Observable[(SHA,String,List[(Char,String)])])] = Observable.from(Future {
    commits
  }).map(commits => (commits.size, {
    Observable.from(commits).sliding(2,1).flatMap(_.toList.collect {
      case (shA,message) :: (shB,_) :: _ => (shA, message, diff(shB, shA))
    })
  }))

  val LOCCache = mutable.HashMap.empty[String,immutable.Map[Lang.Value,CLOC]]
  val moduleCache = mutable.HashMap.empty[SHA,immutable.Map[String,(SHA,Int)]]

  // Calculate for each subdirectory (module) in
  // the /framework/src directory the LOC over time.
  def modulesOverTime(module_root: String = "framework/src") =
  {
    // Test if we have the UNIX tool cloc
    val hasCloc = sys.process.Process(Seq("which", "cloc")).!(ProcessLogger(line => ())) == 0
    if(!hasCloc){
      throw new InterruptedException("Please install CLOC first (eg brew install cloc).")
    }

    Observable.from(Future {
      commits
    }).map(commits => (commits.size, {
      Observable.from(commits).map {
        case (sha, message) => {
          val modules = sys.process.Process(s"git ls-tree ${sha.hash}:$module_root", root).lineStream.toList
          (modules.size, Observable.from(modules).map(line => {
            // Lines look like: 100644 tree 680cc5fa1f59b37428541badd607af74d07a1979	<dir_name>
            val sha = line.substring(12, 12 + 40)
            val module = line.substring(line.indexOf('\t')).trim
            (sha, module) -> LOCCache.getOrElseUpdate(sha, { measureGitTree(SHA(sha), module_root + File.separator + module) })
          }).doOnEach(t => {
            // Store for JSON report
            moduleCache.put(sha, moduleCache.getOrElseUpdate(sha, { immutable.Map.empty }) ++ Map(
              t._1._2 -> (SHA(t._1._1), t._2.values.map(_.code).sum)
            ))
          }))
        }
      }
    }))
  }

  private def measureGitTree(git_obj: SHA, pathPrefix: String = ""): immutable.Map[Lang.Value,CLOC] = {
    immutable.Map.empty ++
    sys.process.Process(Seq("git", "ls-tree", "--", s"""${git_obj.hash}"""), root)
      .lineStream
      .par
      .flatMap { case l =>
        // Lines look like: 100644 blob 680cc5fa1f59b37428541badd607af74d07a1979	<filename>
        (l.substring(7, 11), SHA(l.substring(12, 12 + 40)), pathPrefix + File.separator + l.substring(l.indexOf('\t')).trim) match {
          case ("tree", sha, file) => measureGitTree(sha, file)
          case ("blob", sha, file) => LOCCache.getOrElseUpdate(sha.hash,{ clocForObject(sha, file).map(c => c.lang -> c).toMap})
        }
      }
      .groupBy(_._1)
      .mapValues(_.map(_._2))
      .mapValues(_.reduce((a,b) => CLOC(a.code+b.code, a.comment+b.comment, a.lang)))
      .seq
  }

  private def clocForObject(sha: SHA, filename: String): Option[CLOC]  = {
    import scala.sys.process._
    val summary = Process(Seq("git", "show", sha.hash), root) #| Process(Seq("cloc", "--csv", "--stdin-name", filename, "-"), root) !! ProcessLogger(line => ())
    summary.split('\n').drop(5).headOption.flatMap(l => {
      val a = l.split(',')
      Try(Lang.withName(a(1).toLowerCase)).toOption.map(
        CLOC(a(4).toInt, a(3).toInt, _)
      )
    })
  }

  private def diff(shA: SHA, shB: SHA): List[(Char,String)] = {
    sys.process.Process(Seq("git", "diff", "--name-status", "-C", shA.hash, shB.hash), root)
      .lineStream
      .map(l => l.charAt(0) -> l.substring(1).trim())
      .toList
  }

  private def pathChanges = ???

  import spray.json._
  import DefaultJsonProtocol._
  import Lang.LangFormat
  import SHA.shaFormat

  private def loadState(file: String) = {
    if(Source.fromFile(file).nonEmpty){
      val jsValue = SprayJsonParser.parse(Source.fromFile(file).bufferedReader())
      val map = jsValue.asJsObject.fields.getOrElse("loc", jsValue).convertTo[immutable.Map[String, immutable.Map[Lang.Value, CLOC]]]
      LOCCache.++=(map)
    }
  }

  private def saveState(file: String) = {
    println(s"Shutdown, writing ${LOCCache.keySet.size}")
    val json = immutable.Map(
      "commits" -> commits.zipWithIndex.map(c =>
        c._1._1.hash -> JsObject(
          "sha" -> JsString(c._1._1.hash),
          "message" -> JsString(c._1._2),
          "x" -> JsNumber(c._2)
        )
      ).toMap.toJson,
      "modules" -> immutable.Map(moduleCache.toSeq : _*).mapValues(_.mapValues(triple => {
        JsObject(
          "object" -> JsString(triple._1.hash),
          "loc" -> JsNumber(triple._2)
        )
      })).map(t => t._1.hash -> t._2).toJson,
      "loc" -> immutable.Map(LOCCache.toSeq : _*).toJson
    ).toJson
    val writer = new PrintWriter(new File(file))
    new SprayJsonPrinter(writer, 0)(json)
    writer.close()
  }

  saveFile.filter(new File(_).exists()).foreach(f => { loadState(f) })
  saveFile.foreach(f => sys addShutdownHook { saveState(f) })
}

object GitParser {
  def apply(gitroot: String, branch: String = "master", saveFile: Option[String] = None) = new GitParser(gitroot, branch, saveFile)
}

sealed trait Change {}

object Change {
  def unapply(z: String): Option[Change] = (z.charAt(0), z.substring(1).trim) match {
    case ('A', f) => Some(Add(f))
    case ('D', f) => Some(Del(f))
    case ('M', f) => Some(Mod(f))
    case ('R', Change.renameRegex(p, a, b)) => Some(Rename(a, b, if(p.size>0) Some(p.toInt) else None))
    case u =>
      println(s"Unmatched change:\n$u")
      None
  }

  val filePart = """[^\s\"']+|\"[^\"]*\"|'[^']*'"""
  val renameRegex = s"""R([0-9]*)\s+($filePart)\s+($filePart)"""".r
}

case class Add(file: String) extends Change { def T = 'A' }
case class Del(file: String) extends Change { def T = 'D' }
case class Mod(file: String) extends Change { def T = 'M' }
case class Rename(before: String, after: String, percentage: Option[Int] = None) extends Change { def T = 'R' }
