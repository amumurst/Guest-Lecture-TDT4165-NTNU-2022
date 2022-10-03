package e_2_types2

trait AdServer:
  def start(): Unit

object AdServer:
  def create(): AdServer =
    val database = Database.create[Ad]()
    val console  = Console()
    new AdServer:
      private def addAd(): Unit =
        console.writeValue("Enter ad data: ")
        val adData: Ad       = adFromString(console.readValue())
        val insertedId: AdId = database.saveElement(adData)
        console.writeValue(s"Inserted ad with id: $insertedId")

      private def readAd(): Unit =
        console.writeValue("Enter adId: ")
        val adId: AdId = AdId(console.readValue().toLong)
        console.writeValue(database.getElement(adId).consoleString)

      def start(): Unit = {
        var mode: Mode = Mode.UNKNOWN

        while (mode != Mode.QUIT) {
          console.writeValue("Select mode: quit, add, read: ")
          mode = modeFromString(console.readValue())

          mode match
            case Mode.READ    => readAd()
            case Mode.UNKNOWN => console.writeValue("unknown mode")
            case Mode.ADD     => addAd()
            case Mode.QUIT    => console.writeValue("Goodbye")
        }
      }
