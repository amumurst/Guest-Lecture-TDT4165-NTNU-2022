import cats.effect._

object Main extends IOApp.Simple:
  val server: AdServer = AdServer.create()

  def run: IO[Unit] = IO(server.start())
