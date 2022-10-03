package e_3_option
object _Intro {

  /*
    Option is an sum type ADT with 2 instances
    Some(value) signals that there exists a value
    None signals the absence of a value

    In scala we use Options instead of null to signal absence.
   * */

  enum IntOption:
    case IntNone
    case IntSome(value: Int)

  //imperative-esque absence
  def divideBy(x: Int, y: Int): Int =
    if (y == 0) null.asInstanceOf[Int] else x / y

  val x = divideBy(1, 2)
  val y = x * 2 //Do I need to check for nullability here??

  //functional-esque absence
  def divideByOpt(x: Int, y: Int): Option[Int] =
    if (y == 0) None else Some(x / y)

  val x2 = divideByOpt(1, 2) //The compiler forces us to handle both cases!
  val y2 = x2 match
    case None    => 0
    case Some(a) => a * 2

  /*
  Going from Option[A] to Option[B] via function A=>B
  use Option.map(function)
   */

  val longOption: Option[Long]  = "23".toLongOption          //Some(23)
  val twiceOption: Option[Long] = longOption.map(l => l * 2) //Some(46)

  // Combining options
  val longOption2 = longOption.flatMap(l => longOption.map(l2 => l * l2))
  val forLong2 = for {
    l  <- longOption
    l2 <- longOption
  } yield l + l2
  //These two are equivalent
}
