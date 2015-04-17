import java.io.File
import java.util.concurrent.TimeUnit

import scala.concurrent.duration.Duration
import scala.io.StdIn

object Main {
  def main(args: Array[String]) {
    println(args.toList)
    args.toList match {
      case repo :: rest if !new File(repo).isDirectory() => log(s"""The given repository "$repo" does not exist.""")
      case repo :: rest =>
        val parser = GitParser(repo, saveFile = Some("./metricscache.json"))
        rest match {
          case "modules" :: commonPath :: _ => modules(parser, commonPath)
          case "changes" :: _ => changes(parser)
          case _ => log("Unrecognised option. Type <help> to show available options.")
        }
      case _ =>
        log("Use this utility to generate metrics about a source code repository. Several options:")
        log("\t1. Module metrics")
        log("\t   Usage: <repository> modules <common-path-of-modules>")
    }
  }

  def changes(parser: GitParser): Unit = {
    throw new NotImplementedError("Not yet done / not yet working. This method is able to summarize all changes over time (renames). Could be extended to detect modularisation.")
    val o = parser.revChanges
    if (false)
      o.delay(Duration(1, TimeUnit.SECONDS)).take(2) foreach( {
        case (count, commits) => {
          log(s"Indexing $count commits")
          log(s"0/$count")
          commits.zipWithIndex foreach {
            case ((a, b, c), i) =>
              log(s"\r$i/$count")
            //val summary = c.groupBy(_._1).mapValues(_.size)
          }
        }
      }, (e: Throwable) => log(e + e.getMessage), () => log("Done"))
  }

  def modules(parser: GitParser, commonPath: String): Unit = {
    val m = parser.modulesOverTime(commonPath).delay(Duration(1, TimeUnit.SECONDS)) foreach( {
      case (commit_count, commits) =>
        log(s"Indexing $commit_count commits")
        logupdate(s"commit 0/$commit_count")
        commits.zipWithIndex foreach( {
          case ((module_count: Int, module_cloc), commit_i) =>
            logupdate(s"\rcommit $commit_i/$commit_count")
            module_cloc.zipWithIndex foreach( {
              case (((sha, module_name), map), module_i) =>
                logupdate(s"\rcommit $commit_i/$commit_count module $module_i/$module_count: $module_name")
            }, (e: Throwable) => err(e + e.getMessage))
        }, (e: Throwable) => err(e + e.getMessage))
    }, (e: Throwable) => err(e + e.getMessage), () => log("Done"))

    StdIn.readLine("Press enter to abort.")
  }

  var length = 0
  def log(m: String): Unit = {
    length = m.size
    System.err.println(m)
  }

  def logupdate(m: String): Unit = {
    System.err.print(m.padTo(length, ' '))
    length = m.size
  }

  def err(m: String): Unit = {
    logupdate("Error: "+m.replaceAll("\n", "\t"))
  }
}