package jb.cats.codec

import jb.cats.codec.StringCodec.stringCodec

object DoubleCodec {

  implicit val doubleCodec: Codec[Double] = stringCodec.imap(_.toDouble, _.toString)

  def main(args: Array[String]): Unit = {
    println(implicitly[Codec[Double]].encode(1.0))
  }

}
