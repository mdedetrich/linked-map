package jmh.main

import jmh.main.FirstUnspecialized.BenchmarkState
import org.openjdk.jmh.annotations.{Benchmark, Scope, State}

import scala.collection.immutable.LinkedMap

object FirstUnspecialized {
  @State(Scope.Benchmark)
  class BenchmarkState {
    var createdMap = Map(
      1 -> 1,
      2 -> 2,
      3 -> 3,
      4 -> 4,
      5 -> 5
    )

    var createdLinkedMap = LinkedMap(
      1 -> 1,
      2 -> 2,
      3 -> 3,
      4 -> 4,
      5 -> 5
    )
  }
}

class FirstUnspecialized {

  @Benchmark
  def basicConstructorMap = Map(
    1 -> 1,
    2 -> 2,
    3 -> 3,
    4 -> 4,
    5 -> 5
  )

  @Benchmark
  def basicConstructorLinkedMap(state: BenchmarkState) = LinkedMap(
    1 -> 1,
    2 -> 2,
    3 -> 3,
    4 -> 4,
    5 -> 5
  )

  @Benchmark
  def basicLookupKeyMap(state: BenchmarkState) = state.createdMap(5)

  @Benchmark
  def basicLookupKeyLinkedMap(state: BenchmarkState) = state.createdLinkedMap(5)

}
