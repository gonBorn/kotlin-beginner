plugins {
    kotlin("jvm") version "2.0.0"
    kotlin("plugin.serialization") version "2.0.0"
    id("org.jlleitschuh.gradle.ktlint") version "12.1.1"
    id("org.jetbrains.kotlinx.kover") version "0.8.2"
    application
}

group = "io.github.gonborn"
version = "1.0-SNAPSHOT"

val kotestVersion = "5.9.1"
val arrowVersion = "1.2.4"
val ktorVersion = "2.3.12"

repositories {
    mavenCentral()
}

dependencies {
    // arrow
    implementation("io.arrow-kt:suspendapp:0.4.0")
    implementation("io.arrow-kt:arrow-core:$arrowVersion")
    implementation("io.arrow-kt:arrow-fx-coroutines:$arrowVersion")
    implementation("io.arrow-kt:arrow-resilience:$arrowVersion")
    implementation("io.arrow-kt:suspendapp:0.4.0")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.1")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.6.0")

    // ktor server
    implementation("io.arrow-kt:suspendapp-ktor-jvm:0.4.0")
    implementation("io.ktor:ktor-server-netty:2.3.12")
    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-client-cio:$ktorVersion")

    // kotest
    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
    testImplementation("io.kotest:kotest-property:$kotestVersion")
    // versioned independently of the main kotest project
    testImplementation("io.kotest.extensions:kotest-assertions-arrow:1.4.0")
    // test lib for ktor
    testImplementation("io.ktor:ktor-server-test-host:2.3.12")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}

// 如果要支持 context receiver
kotlin {
    jvmToolchain(21)
    compilerOptions {
        freeCompilerArgs = listOf("-Xcontext-receivers")
    }
}
