import scala.collection.mutable.Map as MutMap

trait Database[Value]:
  def saveElement(value: Value): Long
  def getElement(key: Long): Value

object Database:
  def create[Value](): Database[Value] =
    new Database[Value]:
      val memory: MutMap[Long, Value] = MutMap.empty
      var primaryKey: Long            = 0

      override def saveElement(value: Value): Long = {
        val temp = primaryKey
        primaryKey = primaryKey + 1
        memory(temp) = value
        temp
      }

      override def getElement(key: Long): Value = memory(key)

//Change to KEY (needs 0 and add methods) (KEY: AutoInc)
