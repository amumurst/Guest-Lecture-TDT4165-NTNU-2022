package e_2_types2

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

enum Ad:
  case Car(regnr: String, price: Long)
  case Job(company: String)
  case UnknownAd
extension (ad: Ad)
  def consoleString: String = ad match
    case Ad.Car(regnr, price) => s"car $regnr $price"
    case Ad.Job(company)      => s"job $company"
    case Ad.UnknownAd         => "unknown"

def adFromString(s: String): Ad =
  s.split(' ').toList match
    case "car" :: regnr :: price :: Nil => Ad.Car(regnr, price.toLong)
    case "job" :: company :: Nil        => Ad.Job(company)
    case _                              => Ad.UnknownAd
