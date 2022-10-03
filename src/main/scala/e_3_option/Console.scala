package e_3_option

trait Console:
  def readValue(): String
  def writeValue(s: String): Unit

object Console:
  def apply(): Console =
    new Console:
      def readValue(): String         = scala.io.StdIn.readLine()
      def writeValue(s: String): Unit = println(s)
