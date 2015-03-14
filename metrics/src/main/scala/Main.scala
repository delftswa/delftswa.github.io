

object Main {
  def main(args: Array[String]) {
    val c: List[(String, String, List[(Char, String)])] = new GitParser().revChanges("/Projects/play-shallow").toList
    c.map(t => (t._1, t._2, t._3.groupBy(_._1).mapValues(_.size))).filter(_._3.contains('R')).foreach {
      case (a,b,c) => println(s"${a.take(9)}\t$b\t$c")
    }
//    println(c.toJson.prettyPrint
// )
  }
}