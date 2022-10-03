package e_1_types

opaque type AdId = Long
object AdId:
  def apply(l: Long): AdId = l
  extension (a: AdId)
    def getValue: Long = a
    def inc: AdId      = a + 1

enum Mode:
  case READ, UNKNOWN, ADD, QUIT

def modeFromString(s: String): Mode =
  s match
    case "read" => Mode.READ
    case "add"  => Mode.ADD
    case "quit" => Mode.QUIT
    case _      => Mode.UNKNOWN

case class Ad(price: Long, text: String) {
  def consoleString: String = s"$price $text"
}
def adFromString(s: String): Ad =
  val price = s.takeWhile(_ != ' ').toLong
  val text  = s.dropWhile(_ != ' ')
  Ad(price, text)
