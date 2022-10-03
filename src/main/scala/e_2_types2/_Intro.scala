package e_2_types2
object _Intro {

  /*
    Lists are ADTs!
    They have two possible states. Either we have an element with something else behind it
    or we have reached the end
   */

  enum IntList:
    case Element(element: Int, next: IntList)
    case EmptyIntElement

  /*
    Matching on lists
   */
  val myList = List(1, 2, 3)

  val trippeResult = myList match
    case one :: two :: three :: Nil => one + two + three
    case _                          => 0

  //Calculate product between adjacent elements, then sum of those
  def adjecentProductSum(list: List[Int]) = list
    .sliding(2)
    .collect { case first :: second :: Nil => first * second }
    .sum

  /*
   * Interpolation of string (adding s before " and using $ to escape
   * Use ${a.b} if you need to access field b in value a
   * */

  def getInterpolatedString(v1: String, v2: Int) =
    s"This string is interpolated, with values $v1 and $v2"
}
