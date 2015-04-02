import spray.json.JsonFormat

case class CLOC(code: Int, comment: Int, lang: Lang.Value)

object CLOC {
  import spray.json.DefaultJsonProtocol._
  import Lang.LangFormat
  implicit val clocFormat: JsonFormat[CLOC] = jsonFormat3(CLOC.apply)
}
