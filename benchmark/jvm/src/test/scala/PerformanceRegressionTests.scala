import org.openjdk.jmh.runner.Runner
import org.openjdk.jmh.runner.options.OptionsBuilder
import org.specs2.Specification

import scala.collection.JavaConversions._

class PerformanceRegressionTests extends Specification {

  override def is = s2"""
      firstUnspecialized                         $firstUnspecialized
      """

  def firstUnspecialized = {
    val ratios = List(
      0.61,
      0.75,
      0.59
    )

    val options = new OptionsBuilder()
      .include(
        jmh.main.FirstUnspecialized.getClass.getSimpleName.dropRight(1) + ".*")
      .warmupIterations(10)
      .measurementIterations(10)
      .forks(1)
      .threads(1)
      .build()
    val result = new Runner(options).run()

    val scores = result.toList
      .map(_.getAggregatedResult.getPrimaryResult.getScore)

    val paired = scores.sliding(2, 2).toList.zipWithIndex

    forall(paired)((result: (List[Double], Int)) =>
      result._1(0) / result._1(1) must beGreaterThan(ratios(result._2)))
  }

}
