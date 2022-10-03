trait AdServer:
  def start(): Unit

object AdServer:
  def create(): AdServer =
    val database = Database.create[String]()
    val console  = Console()
    new AdServer:
      private def addAd(): Unit =
        console.writeValue("Enter ad data: ")
        val adData: String   = console.readValue()
        val insertedId: Long = database.saveElement(adData)
        console.writeValue(s"Inserted ad with id: $insertedId")

      private def readAd(): Unit =
        console.writeValue("Enter adId: ")
        val adId: Long = console.readValue().toLong
        console.writeValue(database.getElement(adId))

      def start(): Unit = {
        var mode: String = ""

        while (mode != "quit") {
          console.writeValue("Select mode: quit, add, read: ")
          mode = console.readValue()

          if (mode == "add") addAd()
          else if (mode == "read") readAd()
          else if (mode == "quit") console.writeValue("Goodbye")
          else console.writeValue("unknown mode")
        }
      }
