package assignment1basic

import concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Await, Promise}
import scala.concurrent.Future
import scala.util.{Failure, Success, Try}
object FutureExercise extends App {


  val future1: Future[Int] = Future {
    2592
//    throw new ArithmeticException("Arithmetic Exception")

  }

  val future2: Future[String] = Future {
    "1192"
//    throw new RuntimeException("Runtime Exception")
  }
  //for comprehension to get the combined futures tuple
  val combinedFuture = for {
    result1 <- future1
    result2 <- future2
  } yield (result1, result2)

  combinedFuture.onComplete {
    case Success((result1, result2)) =>
      // handle successful result of combined future
      println(s"Combined result: ($result1, $result2)")
    case Failure(ex) =>
      // handling individual failure cases
      future1.onComplete {
        case Failure(exc) =>
          // handle failure of future1 and logging the error
          println(s"Error in future1: ${exc.getMessage}")
        case _ =>
      }

      future2.onComplete {
        case Failure(exc) =>
          // handle failure of future2 and logging the error
          println(s"Error in future2: ${exc.getMessage}")
        case _ =>
      }
  }
}
