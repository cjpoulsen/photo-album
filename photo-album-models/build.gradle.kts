import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
}

dependencies {
    val serializationJson: String by project

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationJson")
}
