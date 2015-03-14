

object Main {
  def main(args: Array[String]) {
    val o = new GitParser().revChanges("/Projects/play-shallow")
    o subscribe { _ match {
      case (count, commits) => {
        println(s"Indexing $count commits")
        commits.subscribe(n => n match {
          case (a, b, c) => {
            val summary = c.groupBy(_._1).mapValues(_.size)
            println(s"${a.take(9)}\t$b\t$summary")
          }
        })
      }
    }}
//    println(c.toJson.prettyPrint
  }
}