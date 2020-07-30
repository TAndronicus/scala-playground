package jb.ts.variance

class Vehicle

class Car extends Vehicle

class Bus extends Vehicle

class IParking[T](cars: List[T]) {
  def park(list: List[T]): Unit = ???

  def report(): List[T] = ???
}

class CParking[+T](cars: List[T]) {
  def park[S >: T](list: List[S]): Unit = ???

  def report(): List[T] = ???
}

class XParking[-T](cars: List[T]) {
  def park(list: List[T]): Unit = ???

  def report[S <: T](): List[S] = ???
}

object Variance {

  def main(args: Array[String]): Unit = {
    val cParking: CParking[Vehicle] = new CParking[Bus](List())
    cParking.park(List(new Car))
    val xParking: XParking[Bus] = new XParking[Vehicle](List())
    xParking.report()
  }

}
