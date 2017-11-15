package jmh.main

import jmh.main.FirstUnspecialized.BenchmarkState
import org.openjdk.jmh.annotations.{Benchmark, Scope, State}
import org.openjdk.jmh.infra.Blackhole

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
  def basicConstructorMap(bh: Blackhole) = {
    val v = Map(
      1 -> 1,
      2 -> 2,
      3 -> 3,
      4 -> 4,
      5 -> 5
    )
    bh.consume(v)
  }

  @Benchmark
  def basicConstructorLinkedMap(bh: Blackhole) = {
    val v = LinkedMap(
      1 -> 1,
      2 -> 2,
      3 -> 3,
      4 -> 4,
      5 -> 5
    )
    bh.consume(v)
  }

  @Benchmark
  def basicLookupKeyMap(bh: Blackhole, state: BenchmarkState) = {
    val v = state.createdMap(5)
    bh.consume(v)
  }

  @Benchmark
  def basicLookupKeyLinkedMap(bh: Blackhole, state: BenchmarkState) = {
    val v = state.createdLinkedMap(5)
    bh.consume(v)
  }

}
