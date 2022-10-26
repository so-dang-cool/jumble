name := "jumble"
organization := "so.dang.cool"
version := "0.2.2"

// Do not include the scala library as a dependency.
autoScalaLibrary := false

// Do not include the scala version in suffixes.
crossPaths := false

// https://www.scalatest.org
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.14" % "test"
libraryDependencies += "org.scalatestplus" %% "testng-7-5" % "3.2.14.0" % "test"
libraryDependencies += "org.slf4j" % "slf4j-api" % "2.0.3" % "test"
libraryDependencies += "org.slf4j" % "slf4j-simple" % "2.0.3" % "test"

// Publishing via https://www.scala-sbt.org/release/docs/Using-Sonatype.html
credentials += Credentials(Path.userHome / ".sbt" / "sonatype_credentials")
