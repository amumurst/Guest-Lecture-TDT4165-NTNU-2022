package e_7_extra

import scala.collection.mutable.Map as MutMap
import cats.effect.{IO, Ref}

trait Database[Value]:
  def saveElement(value: Value): IO[AdId]
  def getElement(key: AdId): IO[Option[Value]]

object Database:
  private def dbRef[Value]: IO[Ref[IO, Map[AdId, Value]]] =
    Ref.of[IO, Map[AdId, Value]](Map.empty[AdId, Value])
  private val keyRef: IO[Ref[IO, AdId]] =
    Ref.of[IO, AdId](AdId(0))

  private def makeDatabase[Value] = dbRef[Value].flatMap(db => keyRef.map(k => db -> k))

  def create[Value]: IO[Database[Value]] =
    dbRef[Value].flatMap(memory =>
      keyRef.map(primaryKey =>
        new Database[Value]:
          override def saveElement(value: Value): IO[AdId] =
            primaryKey.getAndUpdate(_.inc).flatTap(key => memory.update(_ + (key -> value)))

          override def getElement(key: AdId): IO[Option[Value]] =
            memory.get.map(_.get(key))
      )
    )
