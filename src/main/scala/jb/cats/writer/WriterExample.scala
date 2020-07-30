package jb.cats.writer

import cats.data.Writer
import cats.instances.vector._
import cats.syntax.applicative._
import cats.syntax.writer._

object WriterExample {

  def main(args: Array[String]): Unit = {
    type Logged[A] = Writer[Vector[String], A]
    val writer1 = for {
      a <- 10.pure[Logged]
      _ <- Vector("a", "b", "c").tell
      b <- 32.writer(Vector("x", "y", "z"))
    } yield a + b
    println(writer1.run)
  }

}
