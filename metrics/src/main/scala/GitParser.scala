class GitParser {

  val shaLength = 40

  def revChanges(gitroot: String, branch: String = "master"): TraversableOnce[(String,String,List[(Char,String)])] = {
    val commits = sys.process.Process(Seq("git", "log", branch, "--format=oneline"), new java.io.File(gitroot)).lineStream
    val r = commits
      .map(_.splitAt(shaLength))
      .scanLeft(List.empty[(String,String,List[(Char,String)])])((prev, commit) => (commit._1, prev) match {
      case (_, Nil) => (commit._1, commit._2, List.empty[(Char,String)]) :: Nil
      case (shaA, (shaB,_1,_2) :: rest) => (shaA, commit._2, diff(gitroot, shaB, shaA)) :: (shaB,_1,_2) :: rest
    }).last
    r
  }

  def diff(gitroot: String, shA: String, shB: String): List[(Char,String)] = {
    sys.process.Process(Seq("git", "diff", "--name-status", "-C", shA, shB), new java.io.File(gitroot))
      .lineStream
      .map(l => l.charAt(0) -> l.substring(1).trim())
      .toList
  }
}