plugins {
    kotlin("jvm")
}

dependencies {
    val kotestVersion: String by project
    val mockkVersion: String by project

    implementation(project(":photo-album-models"))

    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")

    testImplementation("io.mockk:mockk:$mockkVersion")
}

tasks.test {
    useJUnitPlatform()
}
