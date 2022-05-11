scalaVersion := "2.12.11"

lazy val root=
  (project in file("cli"))
  .settings(
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.10" % "test"
  )