package test

import org.json4s.jackson.JsonMethods._
import org.json4s.{DefaultFormats, Formats}

case class Person(private val is_old: Boolean, age: Option[Int]) {
  val isOld: Boolean = is_old

  override def toString: String = s"Person{is_old: $is_old, isOld: $isOld, age: $age}"
}

object Json4sExtract {

  implicit val formats: Formats = DefaultFormats

  def main(args: Array[String]): Unit = {
    val jsonStr = """{"is_old": true}"""
    val person = parse(jsonStr).extract[Person]
    println(person.isOld)
    print(person)
  }

}
