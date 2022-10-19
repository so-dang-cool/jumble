ThisBuild / organization := "so.dang.cool"
ThisBuild / organizationName := "jumble"
ThisBuild / organizationHomepage := Some(url("http://github.com/hiljusti/jumble"))
ThisBuild / versionScheme := Some("semver-spec")

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

ThisBuild / description := "Collections for independently typed items."
ThisBuild / licenses := List(
  "Apache 2" -> new URL("http://www.apache.org/licenses/LICENSE-2.0.txt")
)
ThisBuild / homepage := Some(url("https://github.com/hiljusti/jumble"))

ThisBuild / pomIncludeRepository := { _ => false }
ThisBuild / publishTo := {
  val nexus = "https://s01.oss.sonatype.org/"
  if (isSnapshot.value) Some("snapshots" at nexus + "content/repositories/snapshots")
  else Some("releases" at nexus + "service/local/staging/deploy/maven2")
}
ThisBuild / publishMavenStyle := true
