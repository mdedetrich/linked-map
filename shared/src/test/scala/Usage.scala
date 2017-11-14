import scala.collection.immutable.{LinkedMap, VectorMap}

/**
  * Basic usage code to make sure we don't break the API
  */
object Usage {
  val linkedMap = LinkedMap(1 -> 1)
  val vectorMap = VectorMap(1 -> 1)

  val linkedMapToMap = linkedMap.toMap

  val builder = LinkedMap.newBuilder[Int, Int]

  builder += (1 -> 1)

  builder.result()
}
