import spray.json._

object Lang extends Enumeration {
  type Lang = Value
  val scala, java, css, html, md, xml, sql, javascript = Value

  implicit object LangFormat extends RootJsonFormat[Lang.Value] {
    override def read(json: JsValue): Lang.Value = json match {
      case JsString(v) => Lang.withName(v)
      case _ => throw new DeserializationException("Programming Language not found")
    }
    override def write(obj: Lang.Value): JsValue = JsString(obj.toString)
  }
}
