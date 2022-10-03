package e_7_extra
import cats.effect._

object Main extends IOApp.Simple:
  val console = Console()
  def run: IO[Unit] =
    Database.create[Ad].flatMap(database => AdServer.create(database, console).start)
