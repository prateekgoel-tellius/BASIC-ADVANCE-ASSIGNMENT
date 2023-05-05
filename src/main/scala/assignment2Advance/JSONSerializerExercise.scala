package assignment2Advance

import spray.json.DefaultJsonProtocol.{IntJsonFormat, StringJsonFormat}
import spray.json._
object JSONSerializerExercise extends  App {

  // Defining a person case class which contain three parameters name , age and email
  case class Person(name: String, age: Int,email : String)

  // Defining a type class toJson and fromJson method to serialize and deserialize
  trait Converter[T] {
    def toJson(t: T): JsValue
    def fromJson(json: JsValue): T
  }

  // Defining implicits to handle serialization and deserialization of Person class
  implicit object PersonConverter extends Converter[Person] {
    def toJson(person: Person): JsValue = JsObject(
      "name" -> JsString(person.name),
      "age" -> JsNumber(person.age),
      "email" -> JsString(person.email)
    )

    def fromJson(json: JsValue): Person = json match {
      case JsObject(fields) =>
        val name = fields("name").convertTo[String]
        val age = fields("age").convertTo[Int]
        val email = fields("email").convertTo[String]
        Person(name, age, email)
      case _ => throw new DeserializationException("Person expected")
    }
  }

  // Define a generic function to serialize/deserialize JSON using the type class
  def convertToJson[T](t: T)(implicit converter: Converter[T]): String = {
    converter.toJson(t).compactPrint
  }

  def convertFromJson[T](json: String)(implicit converter: Converter[T]): T = {
    converter.fromJson(json.parseJson)
  }

  val person = Person("Prateek", 20,"Prateek.goel@tellius.com")
  val jsonPerson = convertToJson(person)
  println(jsonPerson)

  val OriginalPerson = convertFromJson(jsonPerson)
  println(OriginalPerson)


}