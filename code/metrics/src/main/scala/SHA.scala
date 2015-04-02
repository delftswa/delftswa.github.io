import spray.json.DefaultJsonProtocol._
import spray.json.JsonFormat

case class SHA(hash: String) {
  override def toString = hash
}
object SHA {
  implicit val shaFormat: JsonFormat[SHA] = jsonFormat1(SHA.apply)
}