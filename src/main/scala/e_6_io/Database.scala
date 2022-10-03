package e_6_io

import scala.collection.mutable.Map as MutMap
import cats.effect.IO

trait Database[Value]:
  def saveElement(value: Value): IO[AdId]
  def getElement(key: AdId): IO[Option[Value]]

object Database:
  def create[Value](): Database[Value] =
    new Database[Value]:
      val memory: MutMap[AdId, Value] = MutMap.empty
      var primaryKey: AdId            = AdId(0)

      override def saveElement(value: Value): IO[AdId] = IO {
        val temp = primaryKey
        primaryKey = primaryKey.inc
        memory(temp) = value
        temp
      }

      override def getElement(key: AdId): IO[Option[Value]] = IO(memory.get(key))
