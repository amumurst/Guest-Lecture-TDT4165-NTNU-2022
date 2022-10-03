package e_7_extra
import cats.effect.IO

trait AdServer:
  def start: IO[Unit]

object AdServer:
  def create(database: Database[Ad], console: Console): AdServer =
    new AdServer:
      private val addAd: IO[Unit] =
        console.writeValue("Enter ad data: ") *>
          console.readValue.map(adFromString).flatMap {
            case ad: Ad =>
              database.saveElement(ad).flatMap(insertedId => console.writeValue(s"Inserted ad with id: $insertedId"))
            case err: AdParseError =>
              console.writeValue(err.message)
          }

      private val readAd: IO[Unit] =
        console.writeValue("Enter adId: ") *>
          console.readValue
            .map(n => AdId(n.toLong))
            .flatMap(database.getElement)
            .flatMap {
              case Some(ad) => console.writeValue(ad.consoleString)
              case None     => console.writeValue("Could not find ad!")
            }

      val start: IO[Unit] = {
        console.writeValue("Select mode: quit, add, read: ") *>
          console.readValue.map(modeFromString).flatMap {
            case Right(Mode.READ) => readAd *> start
            case Right(Mode.ADD)  => addAd *> start
            case Right(Mode.QUIT) => console.writeValue("Goodbye")
            case Left(error)      => console.writeValue(error) *> start
          }
      }
