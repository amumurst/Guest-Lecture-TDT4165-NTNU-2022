package e_4_either
object _Intro {

  /*
  Either is also a sum ADT which is more general than
  Option since it also can carry information about the error case
   */

  enum IntStringEither:
    case LeftString(s: String)
    case RightInt(i: Int)

  //imperative-esque error passing
  def divider(x: Int, y: Int): Int =
    if (y == 0) throw new IllegalArgumentException("nooo not a 0 please") else x / y

  val x = divider(1, 2) //Do I need to wrap this in try-catch??
  val y = x * 2

  //functional-esque error passing
  def dividerE(x: Int, y: Int): Either[String, Int] =
    if (y == 0) Left("nooo not a 0 please") else Right(x / y)

  val x2 = dividerE(1, 2)
  val y2 = x2 match
    case Left(value) =>
      println(value)
      0
    case Right(value) => value * 2

  //Lifting options into eithers
  val helloOption: Option[Int]          = Some(42)
  val resultEither: Either[String, Int] = helloOption.toRight("No hello found")

  /*
    Eithers are right-biased
    standard functions are assumed to work on Right-side
   */
  val plusTwo: Either[String, Int] = resultEither.map(i => i + 2)

  //Access to Left has to be explicit
  val stringedThrowableEither: Either[String, Int] = resultEither.left.map(t => s"Error: $t")

  //More generally we have access to disjunctions
  type MyStringIntEither = Int | String

  def foo(m: Int | String): String = m match
    case i: Int    => "Int!"
    case s: String => "String!"
}
