package e_7_extra
import cats.effect.IO

trait Console:
  def readValue: IO[String]
  def writeValue(s: String): IO[Unit]

object Console:
  def apply(): Console =
    new Console:
      def readValue: IO[String]           = IO(scala.io.StdIn.readLine())
      def writeValue(s: String): IO[Unit] = IO.println(s)
