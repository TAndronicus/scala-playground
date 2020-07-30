package jb.cats.monoid

import cats.kernel.Monoid
import cats.syntax.monoid._

object ComplexMonoid {

  implicit val complexMonoid = new Monoid[Complex] {
    override def empty: Complex = Complex(0, 0)

    override def combine(x: Complex, y: Complex): Complex = Complex(x.re + y.re, x.im + y.im)
  }

  def main(args: Array[String]): Unit = {
    println(Monoid[Complex].combine(Complex(2, 3), Complex(5, 7)))
    println(Complex(11, 13).combine(Complex(17, 19)))
  }

}
