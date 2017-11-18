package jmh.main

import jmh.main.SpecializedIntoUnspecialized.BenchmarkState
import org.openjdk.jmh.annotations.{Benchmark, Scope, State}
import org.openjdk.jmh.infra.Blackhole

import scala.collection.immutable.LinkedMap

object SpecializedIntoUnspecialized {
  @State(Scope.Benchmark)
  class BenchmarkState {
    var createdMap = Map(
      1 -> 1,
      2 -> 2,
      3 -> 3,
      4 -> 4
    )

    var createdLinkedMap = LinkedMap(
      1 -> 1,
      2 -> 2,
      3 -> 3,
      4 -> 4
    )
  }
}

class SpecializedIntoUnspecialized {

  @Benchmark
  def addIntoSpecializedMap(bh: Blackhole, state: BenchmarkState) = {
    val v = state.createdMap + (5 -> 5)
    bh.consume(v)
  }

  @Benchmark
  def addIntoSpecializedLinkedMap(bh: Blackhole, state: BenchmarkState) = {
    val v = state.createdLinkedMap + (5 -> 5)
    bh.consume(v)
  }

  @Benchmark
  def updateIntoSpecializedMap(bh: Blackhole, state: BenchmarkState) = {
    val v = state.createdMap.updated(5, 5)
    bh.consume(v)
  }

  @Benchmark
  def updateIntoSpecializedLinkedMap(bh: Blackhole, state: BenchmarkState) = {
    val v = state.createdLinkedMap.updated(5, 5)
    bh.consume(v)
  }
}
