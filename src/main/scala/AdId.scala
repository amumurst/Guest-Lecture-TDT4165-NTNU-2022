opaque type AdId = Long
object AdId:
  def apply(l: Long): Long = l

  extension (a: AdId) def getValue: Long = a
