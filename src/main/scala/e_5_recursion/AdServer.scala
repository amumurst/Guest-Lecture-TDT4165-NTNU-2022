package e_5_recursion

trait AdServer:
  def start(): Unit

object AdServer:
  def create(): AdServer =
    val database = Database.create[Ad]()
    val console  = Console()
    new AdServer:
      private def addAd(): Unit =
        console.writeValue("Enter ad data: ")

        adFromString(console.readValue()) match
          case ad: Ad =>
            val insertedId: AdId = database.saveElement(ad)
            console.writeValue(s"Inserted ad with id: $insertedId")
          case err: AdParseError =>
            console.writeValue(err.message)

      private def readAd(): Unit =
        console.writeValue("Enter adId: ")
        val adId: AdId = AdId(console.readValue().toLong)
        database.getElement(adId) match
          case Some(ad) => console.writeValue(ad.consoleString)
          case None     => console.writeValue("Could not find ad!")

      @scala.annotation.tailrec
      def start(): Unit = {
        console.writeValue("Select mode: quit, add, read: ")
        val mode = modeFromString(console.readValue())

        mode match
          case Right(Mode.READ) => readAd()
          case Right(Mode.ADD)  => addAd()
          case Right(Mode.QUIT) => console.writeValue("Goodbye")
          case Left(error)      => console.writeValue(error)
        if (mode != Right(Mode.QUIT)) start() else ()
      }
