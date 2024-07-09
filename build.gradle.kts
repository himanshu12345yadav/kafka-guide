plugins {
    java
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependency)
}

group = "com.server"
version = "1.0.0"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.spring.jpa)
    implementation(libs.spring.web)
    implementation(libs.spring.kafka)
    compileOnly(libs.lombok)
    developmentOnly(libs.spring.devtools)
    annotationProcessor(libs.lombok)
    testImplementation(libs.spring.test)
    testImplementation(libs.spring.kafka.test)
    testRuntimeOnly(libs.spring.junit)
    implementation(libs.spring.actuator)
    implementation(libs.okhttp)
    implementation(libs.okhttp.sse)
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.register<Copy>("getDependencies") {
    from(sourceSets.main.get().runtimeClasspath)
    into("runtime/")

    doFirst {
        val runtimeDir = File("runtime")
        runtimeDir.deleteRecursively()
        runtimeDir.mkdir()
    }

    doLast {
        File("runtime").deleteRecursively()
    }
}