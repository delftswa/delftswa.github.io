import rx.lang.scala.Observable

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class GitParser {

  val shaLength = 40

  def revChanges(gitroot: String, branch: String = "master"): Observable[(Int,Observable[(String,String,List[(Char,String)])])] = Observable.from(Future {
    sys.process.Process(Seq("git", "log", branch, "--format=oneline"), new java.io.File(gitroot)).lineStream
  }).map(commits => (commits.size, {
    Observable.from(commits.map(_.splitAt(shaLength))).sliding(2,1).flatMap(_.toList.collect {
      case (shA,message) :: (shB,_) :: _ => (shA, message, diff(gitroot, shB, shA))
    })
  }))

  def diff(gitroot: String, shA: String, shB: String): List[(Char,String)] = {
    sys.process.Process(Seq("git", "diff", "--name-status", "-C", shA, shB), new java.io.File(gitroot))
      .lineStream
      .map(l => l.charAt(0) -> l.substring(1).trim())
      .toList
  }
}