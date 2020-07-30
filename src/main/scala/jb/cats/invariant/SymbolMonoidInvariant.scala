package jb.cats.invariant

import cats.Monoid
import cats.instances.string._
import cats.syntax.invariant._
import cats.syntax.semigroup._ // for |+|

object SymbolMonoidInvariant {

  def main(args: Array[String]): Unit = {
    implicit val symbolMonoid: Monoid[Symbol] =
      Monoid[String].imap(Symbol.apply)(_.name)
    Monoid[Symbol].empty
    val a = 'a |+| 'few |+| 'words
    println(a.getClass)
  }

}
