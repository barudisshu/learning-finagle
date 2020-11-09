organization in ThisBuild := "info.galudisu"

name := """learning-finagle"""

maintainer := "Galudisu <galudisu@gmail.com>"

/* scala versions and options */
scalaVersion in ThisBuild := "2.12.8"

// dependencies versions
lazy val finchVersion      = "0.31.0"
lazy val circeVersion      = "0.13.0"
lazy val fasterxmlVersion  = "2.9.10"
lazy val specsVersion      = "4.10.5"
lazy val scalatestVersion  = "3.0.4"
lazy val scalacheckVersion = "1.14.3"

// make version compatible with docker for publishing
ThisBuild / dynverSeparator := "-"

// This work for jdk >= 8u131
javacOptions in Universal := Seq(
  "-J-XX:+UnlockExperimentalVMOptions",
  "-J-XX:+UseCGroupMemoryLimitForHeap",
  "-J-XX:MaxRAMFraction=1",
  "-J-XshowSettings:vm"
)

// These options will be used for *all* versions.
scalacOptions in ThisBuild ++= Seq(
  "-unchecked",
  "-feature",
  "-language:_",
  "-Ypartial-unification",
  "-Xfatal-warnings",
  "-language:higherKinds",
  "-language:implicitConversions",
  "-language:postfixOps",
  "-deprecation",
  "-encoding",
  "utf8"
)
resolvers ++= Seq(
  "Local Maven Repository" at "file://" + Path.userHome.absolutePath + "/.m2/repository",
  Resolver.sonatypeRepo("releases"),
  Resolver.sonatypeRepo("snapshots")
)

mainClass in (Compile, run) := Some("info.galudisu.Main")

libraryDependencies ++= {
  Seq(
    // finch
    "com.github.finagle" %% "finch-core"  % finchVersion,
    "com.github.finagle" %% "finch-circe" % finchVersion,
    // fasterxml
    "com.fasterxml.jackson.core" % "jackson-core"     % fasterxmlVersion,
    "com.fasterxml.jackson.core" % "jackson-databind" % fasterxmlVersion,
    // cire
    "io.circe" %% "circe-generic" % circeVersion,
    // test
    "org.specs2"     %% "specs2-core"       % specsVersion      % Test,
    "org.specs2"     %% "specs2-mock"       % specsVersion      % Test,
    "org.specs2"     %% "specs2-scalacheck" % specsVersion      % Test,
    "org.scalatest"  %% "scalatest"         % scalatestVersion  % Test,
    "org.scalacheck" %% "scalacheck"        % scalacheckVersion % Test
  )
}
