group = "ru.siksmfp.kotlin.streams"
version = "0.1.1"

val appArchiveName = "stream-$version"

plugins {
    application
    kotlin("jvm") version "1.3.20"
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

application {
    mainClassName = "samples.HelloWorldKt"
}

dependencies {
    compile(kotlin("stdlib"))
    compile("info.picocli:picocli:3.9.5")
    testCompile("org.junit.jupiter:junit-jupiter-engine:5.4.1")
}

repositories {
    jcenter()
    mavenCentral()
}

val jar by tasks.getting(Jar::class) {
    archiveName = "$appArchiveName.jar"
    into("META-INF") {
    }
}
