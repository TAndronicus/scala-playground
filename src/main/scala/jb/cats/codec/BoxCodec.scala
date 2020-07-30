package jb.cats.codec

case class Box[A](value: A)

object BoxCodec {
  implicit def boxCodec[A](implicit codec: Codec[A]): Codec[Box[A]] =
    codec.imap(Box(_), _.value)

  def main(args: Array[String]): Unit = {
    import DoubleCodec.doubleCodec
    println(implicitly[Codec[Box[Double]]].encode(Box(1.0)))
  }
}
