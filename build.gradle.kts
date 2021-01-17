import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
  kotlin("jvm") version "1.4.20"
  java
  
  application
  id("com.github.johnrengelman.shadow") version "6.1.0"
}

project.version = "1.0.0"
project.group = "cache.lava"
project.setProperty("mainClassName", "cache.lava.bot.Launcher")

val kotlinVersion = KotlinVersion.CURRENT.toString()

repositories {
  jcenter()
  mavenCentral()
  
  maven(url = "https://jitpack.io")
}

dependencies {
  /* Languages */
  implementation(group = "org.jetbrains.kotlin", name = "kotlin-script-runtime", version = kotlinVersion)
  implementation(group = "org.jetbrains.kotlin", name = "kotlin-compiler-embeddable", version = kotlinVersion)
  implementation(group = "org.jetbrains.kotlin", name = "kotlin-script-util", version = kotlinVersion)
  implementation(group = "org.jetbrains.kotlin", name = "kotlin-scripting-compiler-embeddable", version = kotlinVersion)
  
  /* Discord */
  implementation(group = "net.dv8tion", name = "JDA", version = "4.2.0_168")
  implementation(group = "com.github.Devoxin", name = "Flight", version = "2.0.8")
  
  /* Other */
  implementation(group = "ch.qos.logback", name = "logback-classic", version = "1.2.3")
  implementation(group = "com.typesafe", name = "config", version = "1.4.0")
}

application {
  mainClass.set("cache.lava.bot.Launcher")
}

tasks.apply {
  withType<KotlinCompile> {
    kotlinOptions.suppressWarnings = true
    kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.toString()
  }
  
  withType<ShadowJar> {
    manifest.attributes.apply {
      put("Main-Class", application.getMainClass())
    }
  }
}