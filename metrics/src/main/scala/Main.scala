import java.util.concurrent.TimeUnit

import scala.concurrent.duration.Duration

object Main {
  def main(args: Array[String]) {
    val parser = GitParser("/Projects/play-shallow/", saveFile = Some("./metricscache.json"))

    val o = parser.revChanges
    if(false)
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

    val m = parser.modulesOverTime().delay(Duration(1, TimeUnit.SECONDS)).take(2) foreach ({
      case (commit_count, commits) =>
        log(s"Indexing $commit_count commits")
        log(s"0/$commit_count")
        commits.zipWithIndex foreach ({
          case ((module_count: Int, module_cloc), commit_i) =>
            log(s"\r$commit_i/$commit_count")
            module_cloc foreach ({
              case ((sha, module_name), map) =>
                println(s"$module_name at $sha has CLOC: $map")
            }, (e: Throwable) => log(e + e.getMessage), () => log("Done"))
        }, (e: Throwable) => log(e + e.getMessage), () => log("Done"))
    }, (e: Throwable) => log(e + e.getMessage), () => log("Done"))
//    println(c.toJson.prettyPrint
  }

  def log(m: String): Unit = {
    System.err.println(m)
  }
}