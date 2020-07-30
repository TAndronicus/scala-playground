package jb.cats

import cats.Monad
import cats.syntax.applicative._

import scala.language.higherKinds

object GenericMonadComposer {

  def compose[M1[_] : Monad, M2[_] : Monad]: Unit = {
    type Composed[A] = M1[M2[A]]

    new Monad[Composed] {
      override def flatMap[A, B](fa: Composed[A])(f: A => Composed[B]): Composed[B] = ???

      override def tailRecM[A, B](a: A)(f: A => Composed[Either[A, B]]): Composed[B] = ???

      override def pure[A](x: A): Composed[A] = x.pure[M2].pure[M1]
    }
  }


}
