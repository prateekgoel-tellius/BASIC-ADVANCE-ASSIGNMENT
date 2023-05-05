package assignment1basic

import java.util.Random
import scala.annotation.tailrec
import scala.concurrent.duration.FiniteDuration
import scala.util.{Failure, Success, Try}

object RetryExercise extends App{
  @tailrec
  def retry[T](f: => T, maxRetries: Int): T = {
    Try(f) match {
      case Success(value) => value
      case Failure(ex) =>
        if (maxRetries > 0) {
          Thread.sleep(1000)
          retry(f, maxRetries - 1)
        } else {
          throw ex
        }
    }
  }
  val random  = new Random()
  // action function to retry
  def action: String = {
    println("Executing action function...")
    //  we are getting an random value and checking that if it is lesser than 5 then we are throwing the exception
    val randomValue = random.nextInt(10)
    if (randomValue < 5) {
      throw new RuntimeException("Failed attempt")
    }
    "Successful result"
  }


  // Call the retry function with the action function
  val result = retry(action, maxRetries = 50)
  println(result)
}
