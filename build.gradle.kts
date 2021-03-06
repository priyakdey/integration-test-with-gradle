import org.gradle.api.JavaVersion.VERSION_16

/*
 * This file was generated by the Gradle 'init' task.
 *
 * This is a general purpose Gradle build.
 * Learn more about Gradle by exploring our samples at https://docs.gradle.org/7.1.1/samples
 */

plugins {
    id("org.springframework.boot") version "2.5.5"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    java
}

group = "com.priyakdey"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = VERSION_16

repositories {
    mavenCentral()
}

sourceSets {
    create("integrationTest") {
        java.srcDir("src/integrationTest/java")
        resources.srcDir("src/integrationTest/resources")
        compileClasspath += sourceSets["main"].output + configurations["testRuntimeClasspath"]
        runtimeClasspath += output + compileClasspath + sourceSets["test"].runtimeClasspath
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
    useJUnitPlatform() {
        includeTags("unit")     // Include all test cases which are tagged as "unit"
    }
}

// Custom task to run the integration test cases~
task<Test>("integrationTest") {
    description = "Run the integration test cases"
    group = "verification"
    useJUnitPlatform() {
        includeTags("non-unit")     // Include all test cases which are tagged as "non-unit"
    }
    testClassesDirs = sourceSets["integrationTest"].output.classesDirs
    classpath = sourceSets["integrationTest"].runtimeClasspath
}