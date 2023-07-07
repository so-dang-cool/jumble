// tl;dr: sbt test doc publishSigned
// TODO: https://www.scala-sbt.org/release/docs/Using-Sonatype.html#Integrate+with+the+release+process

ThisBuild / description := "Collections for independently typed items."
ThisBuild / organizationName := "so.dang.cool"
ThisBuild / organizationHomepage := Some(url("https://so.dang.cool"))
ThisBuild / versionScheme := Some("semver-spec")

ThisBuild / publishMavenStyle := true

ThisBuild / scmInfo := Some(
  ScmInfo(
    url("https://github.com/booniepepper/jumble"),
    "scm:git@github.com:booniepepper/jumble.git"
  )
)
ThisBuild / developers := List(
  Developer(
    id = "booniepepper",
    name = "J.R. Hill",
    email = "justin@so.dang.cool",
    url = url("https://so.dang.cool")
  )
)

ThisBuild / licenses := List(
  "BSD-3-Clause" -> new URL("https://github.com/booniepepper/jumble/blob/core/LICENSE")
)
ThisBuild / homepage := Some(url("https://github.com/booniepepper/jumble"))

ThisBuild / pomIncludeRepository := { _ => false }
ThisBuild / publishTo := {
  val nexus = "https://s01.oss.sonatype.org/"
  if (isSnapshot.value) Some("snapshots" at nexus + "content/repositories/snapshots")
  else Some("releases" at nexus + "service/local/staging/deploy/maven2")
}
ThisBuild / publishMavenStyle := true
