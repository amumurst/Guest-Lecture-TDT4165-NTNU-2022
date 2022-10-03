package e_6_io
import cats.effect._

object Main extends IOApp.Simple:
  val server: AdServer = AdServer.create()

  def run: IO[Unit] = server.start
