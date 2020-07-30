package jb.cats.functor

import cats.Functor
import cats.syntax.functor._

object Functors {

  implicit val treeFunctor: Functor[Tree] = new Functor[Tree] {
    override def map[A, B](fa: Tree[A])(f: A => B): Tree[B] = fa match {
      case Branch(left, right) => Branch(map(left)(f), map(right)(f))
      case Leaf(value) => Leaf(f(value))
    }
  }

}

object TreeFunctorTest {
  def main(args: Array[String]): Unit = {
    val tree = Branch(Branch(Leaf(2), Leaf(3)), Leaf(5))
    println(Functors.treeFunctor.map(tree)(_ * 2 + 1))
    import Functors.treeFunctor
    Tree.leaf(1).map(_ * 2)
  }
}
