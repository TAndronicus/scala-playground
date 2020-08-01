package jb.cats.semigroupal

import cats.Semigroupal
import cats.data.Validated
import cats.instances.list._ // for Monoid

object SemigroupalExample {
  type AllErrorsOr[A] = Validated[List[String], A]

  def main(args: Array[String]): Unit = {
    val twoErrors: AllErrorsOr[(Nothing, Nothing)] = Semigroupal[AllErrorsOr].product(
      Validated.invalid(List("Error 1")),
      Validated.invalid(List("Error 2"))
    )
    val oneError: AllErrorsOr[(Nothing, List[String])] = Semigroupal[AllErrorsOr].product(
      Validated.invalid(List("Error 1")),
      Validated.valid(List("Error 2"))
    )
    println(twoErrors)
    println(oneError)
  }
}
