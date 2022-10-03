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

  /*
    Opaque types
   */

  def saveUserForAd(userId: Long, adId: Long): Unit = ???

  val aId = 1
  val uId = 9

  saveUserForAd(aId, uId)
  saveUserForAd(adId = aId, userId = uId)

  type MyAdId   = Long
  type MyUserId = Long
  def saveUserForAd2(userId: MyUserId, adId: MyAdId): Unit = ???
  saveUserForAd2(aId, uId) //... still works

  opaque type OAdId   = Long
  opaque type OUserId = Long
  def saveUserForAd3(userId: OUserId, adId: OAdId): Unit = ???
  //saveUserForAd3(aId, uId) // Would not compile if defined in another scope than this. In here
  //the function still "knows" that these are long

  def usePositiveNumber(somePositiveNumber: Long): Unit = ???

  //Opaque types are types only the compiler sees. They help us from making mistakes, while having zero
  //overhead runtime
  opaque type PositiveLong = Long
  object PositiveLong:
    def apply(l: Long) =
      assert(l >= 0)
      l
    extension (p: PositiveLong) def value = p
}
