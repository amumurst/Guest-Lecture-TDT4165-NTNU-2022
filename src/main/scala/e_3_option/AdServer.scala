package e_3_option

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
          case Some(ad) =>
            val insertedId: AdId = database.saveElement(ad)
            console.writeValue(s"Inserted ad with id: $insertedId")
          case None =>
            console.writeValue(s"Bad format, could not insert")

      private def readAd(): Unit =
        console.writeValue("Enter adId: ")
        val adId: AdId = AdId(console.readValue().toLong)
        database.getElement(adId) match
          case Some(ad) => console.writeValue(ad.consoleString)
          case None     => console.writeValue("Could not find ad!")

      def start(): Unit = {
        var mode: Option[Mode] = None

        while (!mode.contains(Mode.QUIT)) {
          console.writeValue("Select mode: quit, add, read: ")
          mode = modeFromString(console.readValue())

          mode match
            case Some(Mode.READ) => readAd()
            case Some(Mode.ADD)  => addAd()
            case Some(Mode.QUIT) => console.writeValue("Goodbye")
            case None            => console.writeValue("unknown mode")
        }
      }
