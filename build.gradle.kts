import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.6.6"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.spring") version "1.6.10"
}

group = "com.sumup.test"
version = "0.0.1-SNAPSHOT"
description = "Drone Challenge"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}


dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web:2.6.6")
    implementation("org.springdoc:springdoc-openapi-ui:1.6.7")
    implementation("org.springframework.boot:spring-boot-starter-validation:2.6.6")
    implementation("org.easymock:easymock:2.0")
    runtimeOnly("org.springframework.boot:spring-boot-devtools:2.6.6")
    testImplementation("org.springframework.boot:spring-boot-starter-test:2.6.6")
    testImplementation("io.mockk:mockk:1.12.3")
    testImplementation("com.ninja-squad:springmockk:3.1.1")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
