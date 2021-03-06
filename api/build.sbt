import scala.sys.process.Process

val Http4sVersion = "0.21.1"
val CirceVersion = "0.13.0"
val Specs2Version = "4.8.3"
val LogbackVersion = "1.2.3"

lazy val commitHash: String = Process("git rev-parse --short HEAD").!!.trim()

lazy val dockerTag: String = {
  val date = Process("date +%Y%m%d_%H%M%S").!!.trim()
  s"$date-$commitHash".trim()
}

lazy val root = (project in file("."))
  .settings(
    organization := "codefresh",
    name := "server",
    scalaVersion := "2.13.1",
    packageName in Docker := "http4s-hello-world",
    dockerExposedPorts := Seq(8080),
    dockerRepository := Some("markdj"),
    version in Docker := dockerTag,
    libraryDependencies ++= Seq(
      "org.http4s" %% "http4s-blaze-server" % Http4sVersion,
      "org.http4s" %% "http4s-blaze-client" % Http4sVersion,
      "org.http4s" %% "http4s-circe" % Http4sVersion,
      "org.http4s" %% "http4s-dsl" % Http4sVersion,
      "io.circe" %% "circe-generic" % CirceVersion,
      "org.specs2" %% "specs2-core" % Specs2Version % "test",
      "ch.qos.logback" % "logback-classic" % LogbackVersion
    ),
    addCompilerPlugin("org.typelevel" %% "kind-projector" % "0.10.3"),
    addCompilerPlugin("com.olegpy" %% "better-monadic-for" % "0.3.0")
  )
  .enablePlugins(JavaAppPackaging, DockerPlugin)

scalacOptions ++= Seq(
  "-deprecation",
  "-encoding",
  "UTF-8",
  "-language:higherKinds",
  "-language:postfixOps",
  "-feature",
  "-Xfatal-warnings"
)
