package jb.cats.codec

object StringCodec {
 val stringCodec: Codec[String] = new Codec[String] {
  override def encode(value: String): String = value

  override def decode(value: String): String = value
 }
}
