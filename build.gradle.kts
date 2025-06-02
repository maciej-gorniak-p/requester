plugins {
    kotlin("jvm") version "1.9.23"
    idea
    application
    `maven-publish`
    id("org.springframework.boot") version "3.3.0"
    id("org.jetbrains.kotlin.plugin.spring") version "2.1.10"
}

group = "mg.requester"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web:3.3.12")
    implementation("org.jetbrains.kotlin:kotlin-reflect:2.1.10")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}