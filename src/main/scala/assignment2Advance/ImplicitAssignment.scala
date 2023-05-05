package assignment2Advance

object ImplicitAssignment extends App{
  implicit val greetStart: String = "Hi"
  def greet(name:String)(implicit greetS: String)= {
    //here we are having greetS which is an implicit parameter
    println(s"$greetS, $name")
  }
  greet("Prateek")

}
