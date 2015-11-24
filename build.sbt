import sbt._
import sbt.Keys._
import android.Keys._

lazy val commonSettings = Seq(
  organization := "com.codemotion2015.workshop",
  version := "0.1.0",
  scalaVersion := "2.11.7",
  javacOptions ++= Seq("-target", "1.7", "-source", "1.7"),
  unmanagedBase := file(".") / "libs")

lazy val hexagon = Project(id = "hexagon", base = file("modules/hexagon"))
  .settings(commonSettings: _*)
  .settings(
    exportJars := true,
    libraryDependencies ++= Seq(
      "junit" % "junit" % "4.12" % "test",
      "com.novocode" % "junit-interface" % "0.11" % "test"
    )
  )

lazy val adapters = Project(id = "adapters", base = file("modules/adapters"))
  .settings(commonSettings: _*)
  .settings(
    exportJars := true,
    libraryDependencies ++= Seq(
      "com.squareup.retrofit" % "retrofit" % "1.9.0",
      "com.google.code.gson" % "gson" % "2.4",
      "junit" % "junit" % "4.12" % "test",
      "com.novocode" % "junit-interface" % "0.11" % "test",
      "com.squareup.okhttp" % "mockwebserver" % "2.4.0" % "test")
  )
  .dependsOn(hexagon)

  lazy val app = Project(id = "app", base = file("modules/app"))
    .settings(commonSettings: _*)
    .settings(
      android.Plugin.androidBuild,
      libraryDependencies ++= Seq(
        aar("com.android.support" % "appcompat-v7" % "23.1.1"),
        aar("com.android.support" % "recyclerview-v7" % "23.1.1"),
        aar("com.android.support" % "cardview-v7" % "23.1.1"),
        aar("com.android.support" % "design" % "23.1.1"),
        "com.android.support.test" % "runner" % "0.4.1",
        "com.android.support.test" % "rules" % "0.4.1",
        "com.android.support.test.espresso" % "espresso-core" % "2.2.1"
      ),
      debugIncludesTests in Android := true,
      instrumentTestRunner in Android := "android.support.test.runner.AndroidJUnitRunner",
      packagingOptions in Android := PackagingOptions(excludes = Seq(
        "LICENSE.txt",
        "META-INF/LICENSE",
        "META-INF/LICENSE.txt",
        "META-INF/NOTICE",
        "META-INF/NOTICE.txt")),
      platformTarget in Android := "android-23",
      autoScalaLibrary := false,
      proguardScala in Android := true,
      proguardOptions in Android ++= proguardCommons,
      proguardCache in Android := Seq.empty
    )
    .dependsOn(adapters)

    lazy val proguardCommons = Seq(
      "-ignorewarnings",
      "-keep class scala.Dynamic",
      "-keep class com.android.** { *; }",
      "-keep class com.codemotion2015.workshop.** { *; }",
      "-keep public class * extends junit.framework.TestCase",
      "-keepclassmembers class * extends junit.framework.TestCase { *; }")
