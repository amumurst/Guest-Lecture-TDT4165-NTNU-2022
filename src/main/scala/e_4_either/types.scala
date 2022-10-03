package e_4_either

opaque type AdId = Long
object AdId:
  def apply(l: Long): AdId = l
  extension (a: AdId)
    def getValue: Long = a
    def inc: AdId      = a + 1

enum Mode:
  case READ, ADD, QUIT

def modeFromString(s: String): Either[String, Mode] =
  s match
    case "read"  => Right(Mode.READ)
    case "add"   => Right(Mode.ADD)
    case "quit"  => Right(Mode.QUIT)
    case unknown => Left(s"Mode $unknown is not supported")

enum Ad:
  case Car(regnr: String, price: Long)
  case Job(company: String)
extension (ad: Ad)
  def consoleString: String = ad match
    case Ad.Car(regnr, price) => s"car $regnr $price"
    case Ad.Job(company)      => s"job $company"

def adFromString(s: String): AdParseError | Ad =
  s.split(' ').toList match
    case "car" :: regnr :: price :: Nil =>
      safeParsePrice(price) match
        case price: Long         => Ad.Car(regnr, price)
        case error: AdParseError => error
    case "car" :: rest           => AdParseError.ArgumentNumberError(rest.length, 2)
    case "job" :: company :: Nil => Ad.Job(company)
    case "job" :: rest           => AdParseError.ArgumentNumberError(rest.length, 1)
    case other :: _              => AdParseError.UnknownAdTypeError(other)
    case Nil                     => AdParseError.UnknownAdTypeError("NOT_SPECIFIED")

enum AdParseError(val message: String):
  case ArgumentNumberError(had: Int, expected: Int)
      extends AdParseError(s"Ad had $had arguments, $expected was expected")
  case UnknownAdTypeError(adType: String) extends AdParseError(s"Unknown ad type $adType")
  case LongParseError(notANumber: String) extends AdParseError(s"$notANumber is not a number")
  case NegativeNumber(number: Long) extends AdParseError(s"$number was under 0")

def safeParsePrice(price: String): Long | AdParseError.LongParseError | AdParseError.NegativeNumber =
  price.toLongOption match
    case Some(price) if price >= 0 => price
    case Some(price)               => AdParseError.NegativeNumber(price)
    case None                      => AdParseError.LongParseError(price)
