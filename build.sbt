name := "linked-map"

// shadow sbt-scalajs' crossProject and CrossType until Scala.js 1.0.0 is released
import sbtcrossproject.crossProject

val currentScalaVersion = "2.11.12"
val scala210Version = "2.10.6"
val scala212Version = "2.12.4"
//
scalaVersion in ThisBuild := currentScalaVersion

crossScalaVersions in ThisBuild := Seq(currentScalaVersion,
                                       scala212Version,
                                       scala210Version)

scalafmtVersion in ThisBuild := "1.3.0"

autoAPIMappings := true

val flagsFor10 = Seq(
  "-Xlint",
  "-Yclosure-elim",
  "-Ydead-code"
)

val flagsFor11 = Seq(
  "-Xlint:_",
  "-Yconst-opt",
  "-Ywarn-infer-any",
  "-Yclosure-elim",
  "-Ydead-code",
  "-Xsource:2.12" // required to build case class construction
)

val flagsFor12 = Seq(
  "-Xlint:_",
  "-Ywarn-infer-any",
  "-opt-inline-from:<sources>"
)

lazy val root = project
  .in(file("."))
  .aggregate(linkedMapJs, linkedMapJVM)
  .settings(
    publish := {},
    publishLocal := {}
  )

lazy val linkedMap = crossProject(JSPlatform, JVMPlatform)
  .in(file("."))
  .settings(
    name := "linked-map",
    version := "0.1.0-SNAPSHOT",
    scalacOptions ++= Seq(
      "-encoding",
      "UTF-8",
      "-deprecation", // warning and location for usages of deprecated APIs
      "-feature", // warning and location for usages of features that should be imported explicitly
      "-unchecked", // additional warnings where generated code depends on assumptions
      "-Xlint", // recommended additional warnings
      "-Xcheckinit", // runtime error when a val is not initialized due to trait hierarchies (instead of NPE somewhere else)
      "-Ywarn-adapted-args", // Warn if an argument list is modified to match the receiver
      "-Ywarn-value-discard", // Warn when non-Unit expression results are unused
      "-Ywarn-inaccessible",
      "-Ywarn-dead-code"
    ),
    scalacOptions ++= {
      CrossVersion.partialVersion(scalaVersion.value) match {
        case Some((2, n)) if n >= 12 =>
          flagsFor12
        case Some((2, n)) if n == 11 =>
          flagsFor11
        case Some((2, n)) if n == 10 =>
          flagsFor10
      }
    }
  )
  .jvmSettings(
    // Add JVM-specific settings here
  )
  .jsSettings(
    // Add JS-specific settings here
  )

lazy val benchmark = crossProject(JSPlatform, JVMPlatform)
  .in(file("benchmark"))
  .jvmSettings(
    testFrameworks += new TestFramework("org.scalameter.ScalaMeterFramework"),
    libraryDependencies ++= Seq(
      "com.storm-enroute" %% "scalameter" % "0.8.2" % Test
    )
  )
  .dependsOn(linkedMap)

lazy val linkedMapJVMBenchmark = benchmark.jvm
lazy val linkedMapJsBenchmark = benchmark.js

lazy val linkedMapJVM = linkedMap.jvm
lazy val linkedMapJs = linkedMap.js
