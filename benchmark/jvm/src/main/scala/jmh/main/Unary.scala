package jmh.main

import jmh.main.Unary.BenchmarkState
import org.openjdk.jmh.annotations.{Benchmark, Scope, State}

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
  def basicConstructorLinkedMap = {
    LinkedMap(1 -> 1)
  }

  @Benchmark
  def basicConstructorMap = {
    Map(1 -> 1)
  }

  @Benchmark
  def basicLookupKeyMap(state: BenchmarkState) = state.createdMap(1)

  @Benchmark
  def basicLookupKeyLinkedMap(state: BenchmarkState) = state.createdLinkedMap(1)
}
