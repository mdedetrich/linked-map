package jmh.main

import jmh.main.Unary.BenchmarkState
import org.openjdk.jmh.annotations.{Benchmark, Scope, State}
import org.openjdk.jmh.infra.Blackhole

import scala.collection.immutable.LinkedMap

object Unary {
  @State(Scope.Benchmark)
  class BenchmarkState {
    var createdMap = Map(
      1 -> 1
    )

    var createdLinkedMap = LinkedMap(
      1 -> 1
    )
  }
}

class Unary {
  @Benchmark
  def basicConstructorLinkedMap(bh: Blackhole) = {
    val v = LinkedMap(1 -> 1)
    bh.consume(v)
  }

  @Benchmark
  def basicConstructorMap(bh: Blackhole) = {
    val v = Map(1 -> 1)
    bh.consume(v)
  }

  @Benchmark
  def basicLookupKeyMap(bh: Blackhole, state: BenchmarkState) = {
    val v = state.createdMap(1)
    bh.consume(v)
  }

  @Benchmark
  def basicLookupKeyLinkedMap(bh: Blackhole, state: BenchmarkState) = {
    val v = state.createdLinkedMap(1)
    bh.consume(v)
  }
}
