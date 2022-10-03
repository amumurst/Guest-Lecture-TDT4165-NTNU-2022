package e_3_option

opaque type AdId = Long
object AdId:
  def apply(l: Long): AdId = l
  extension (a: AdId)
    def getValue: Long = a
    def inc: AdId      = a + 1

enum Mode:
  case READ, ADD, QUIT

def modeFromString(s: String): Option[Mode] =
  s match
    case "read" => Some(Mode.READ)
    case "add"  => Some(Mode.ADD)
    case "quit" => Some(Mode.QUIT)
    case _      => None

enum Ad:
  case Car(regnr: String, price: Long)
  case Job(company: String)
extension (ad: Ad)
  def consoleString: String = ad match
    case Ad.Car(regnr, price) => s"car $regnr $price"
    case Ad.Job(company)      => s"job $company"

def adFromString(s: String): Option[Ad] =
  s.split(' ').toList match
    case "car" :: regnr :: price :: Nil => Some(Ad.Car(regnr, price.toLong))
    case "job" :: company :: Nil        => Some(Ad.Job(company))
    case _                              => None
