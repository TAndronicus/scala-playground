package jb.cats.semigroupal

import cats.data.Validated
import cats.instances.list._
import cats.syntax.apply._
import cats.syntax.either._

case class User(name: String, age: Int)

object ValidatedExercise {

  def getValueFF(params: Map[String, String], field: String): Either[String, String] = {
    params.get(field) match {
      case Some(value) => value.asRight[String]
      case None => s"Value for $field is not present".asLeft[String]
    }
  }

  def parseIntFF(toParse: String): Either[String, Int] = {
    Either.catchOnly[NumberFormatException](toParse.toInt)
      .leftMap(_ => s"$toParse is not a number")
  }

  def notBlankFF(toValidate: String): Either[String, String] = {
    if (toValidate.isEmpty) "Name not given".asLeft[String]
    else toValidate.asRight[String]
  }

  def positiveFF(toValidate: Int): Either[String, Int] = {
    if (toValidate <= 0) s"$toValidate is not positive".asLeft[Int]
    else toValidate.asRight[String]
  }

  def readNameFF(params: Map[String, String]): Either[String, String] = {
    for {
      extracted <- getValueFF(params, "name")
      validated <- notBlankFF(extracted)
    } yield validated
  }

  def readAgeFF(params: Map[String, String]): Either[String, Int] = {
    for {
      extracted <- getValueFF(params, "age")
      validatedInt <- parseIntFF(extracted)
      validatedPositive <- positiveFF(validatedInt)
    } yield validatedPositive
  }

  def readUserFF(params: Map[String, String]): Either[String, User] = {
    for {
      name <- readNameFF(params)
      age <- readAgeFF(params)
    } yield User(name, age)
  }

  def readUserFS(params: Map[String, String]): Validated[List[String], User] = {
    (
      readNameFF(params).toValidated.leftMap(List(_)),
      readAgeFF(params).toValidated.leftMap((List(_)))
      ).mapN(User.apply)
  }

  def main(args: Array[String]): Unit = {
    println(readUserFF(Map("name" -> "Joe")))
    println(readUserFF(Map("name" -> "", "age" -> "15")))
    println(readUserFF(Map("name" -> "Joe", "age" -> "a")))
    println(readUserFF(Map("name" -> "", "age" -> "a")))
    println(readUserFF(Map("name" -> "", "age" -> "-5")))
    println(readUserFF(Map("name" -> "Joe", "age" -> "-5")))
    println(readUserFF(Map("name" -> "Joe", "age" -> "25")))
    println(readUserFS(Map("name" -> "Joe")))
    println(readUserFS(Map("name" -> "", "age" -> "15")))
    println(readUserFS(Map("name" -> "Joe", "age" -> "a")))
    println(readUserFS(Map("name" -> "", "age" -> "a")))
    println(readUserFS(Map("name" -> "", "age" -> "-5")))
    println(readUserFS(Map("name" -> "Joe", "age" -> "-5")))
    println(readUserFS(Map("name" -> "Joe", "age" -> "25")))
  }

}
