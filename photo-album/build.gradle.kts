plugins {
    kotlin("jvm")
    application
}

dependencies {
    val kotestVersion: String by project
    val cliVersion: String by project
    val ktorVersion: String by project
    val serializationJsonVersion: String by project
    val wireMockVersion: String by project
    val kotestWireMockVersion: String by project

    implementation(project(":photo-album-core"))
    implementation(project(":photo-album-models"))

    implementation("com.github.ajalt.clikt:clikt:$cliVersion")

    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-client-java:$ktorVersion")
    implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")

    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")

    testImplementation("io.ktor:ktor-client-mock:$ktorVersion")
    testImplementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
    testImplementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
    testImplementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationJsonVersion")

    testImplementation("com.github.tomakehurst:wiremock-jre8:$wireMockVersion")
    testImplementation("io.kotest.extensions:kotest-extensions-wiremock:$kotestWireMockVersion")
}

application {
    mainClass.set("me.cpoulsen.photo.album.PhotoAlbumApplicationKt")
}

tasks.test {
    useJUnitPlatform()
}
