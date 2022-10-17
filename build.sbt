name := "jumble"
description := "Collections for independently typed items."
organization := "so.dang.cool"
version := "0.1.0"

// Do not include the scala library as a dependency.
autoScalaLibrary := false

// Do not include the scala version in suffixes.
crossPaths := false

publishMavenStyle := true

libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.14" % "test"
