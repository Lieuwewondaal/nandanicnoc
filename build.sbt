name := """nandanicnoc"""

version := "0.1"

scalaVersion := "2.11.4"

libraryDependencies ++= Seq(
  jdbc,
  javaEbean,
  "org.webjars" % "jquery" % "2.1.1",
  "org.webjars.bower" % "jquery-dateFormat" % "1.0.2",
  "org.webjars" % "bootstrap" % "3.3.1"
)     

lazy val root = (project in file(".")).enablePlugins(PlayJava)


fork in run := true

fork in run := true

fork in run := true
