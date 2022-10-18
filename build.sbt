name := "jumble"
description := "Collections for independently typed items."
organization := "so.dang.cool"
version := "0.1.0"

// Do not include the scala library as a dependency.
autoScalaLibrary := false

// Do not include the scala version in suffixes.
crossPaths := false

publishMavenStyle := true

// https://www.scalatest.org
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.14" % "test"
libraryDependencies += "org.scalatestplus" %% "testng-7-5" % "3.2.14.0" % "test"
libraryDependencies += "org.slf4j" % "slf4j-api" % "2.0.3" % "test"
libraryDependencies += "org.slf4j" % "slf4j-simple" % "2.0.3" % "test"
