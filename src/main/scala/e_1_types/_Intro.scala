package e_1_types
object _Intro {
  /*
   * Sum type (ADT) for Pet
   * */

  enum Pet:
    case Cat(name: String)
    case Fish(name: String, teeth: Long)
    case Squid(name: String, age: Int)
    case LochNessMonster

  import Pet._

  val someCuteCat = Cat("Pusefinn")
  val funnySquid  = Squid("Squidward", 3)

  val petList: List[Pet] = List(someCuteCat, funnySquid)

  /*Introducing a function that takes any pet */
  def royalPetName(p: Pet): String = p match
    case Cat(name)                     => s"Emperor $name"
    case Fish(name, _)                 => s"Baron $name"
    case Squid(name, age) if age >= 10 => s"Duke $name"
    case Squid(name, age)              => s"Prince $name"
    case LochNessMonster               => s"Nessie the Loch Ness Monster"

  val royalPetNames: List[String] = petList.map(royalPetName)

  /*
    Product type for Person
   */

  case class Person(name: String, pet: Pet)

  val bob  = Person("Bob", Cat("Kit"))
  val john = bob.copy(name = "John")
}
