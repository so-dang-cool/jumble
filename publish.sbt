ThisBuild / description := "Collections for independently typed items."
ThisBuild / organizationName := "so.dang.cool"
ThisBuild / organizationHomepage := Some(url("https://so.dang.cool"))
ThisBuild / versionScheme := Some("semver-spec")

ThisBuild / publishMavenStyle := true

ThisBuild / scmInfo := Some(
  ScmInfo(
    url("https://github.com/hiljusti/jumble"),
    "scm:git@github.com:hiljusti/jumble.git"
  )
)
ThisBuild / developers := List(
  Developer(
    id = "hiljusti",
    name = "J.R. Hill",
    email = "hiljusti@so.dang.cool",
    url = url("http://so.dang.cool")
  )
)

ThisBuild / licenses := List(
  "BSD-3-Clause" -> new URL("https://github.com/hiljusti/jumble/blob/core/LICENSE")
)
ThisBuild / homepage := Some(url("https://github.com/hiljusti/jumble"))

ThisBuild / pomIncludeRepository := { _ => false }
ThisBuild / publishTo := {
  val nexus = "https://s01.oss.sonatype.org/"
  if (isSnapshot.value) Some("snapshots" at nexus + "content/repositories/snapshots")
  else Some("releases" at nexus + "service/local/staging/deploy/maven2")
}
ThisBuild / publishMavenStyle := true
