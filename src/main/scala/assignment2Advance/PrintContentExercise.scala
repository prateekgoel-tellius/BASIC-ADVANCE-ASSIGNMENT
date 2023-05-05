package assignment2Advance

object PrintContentExercise extends App{
  implicit class ListContent[T](list : List[T]){
    def printContent(): Unit = {
      list.foreach(println)
    }
  }
  val list = List("Prateek","Goel","22","Meerut")
  list.printContent()



}
