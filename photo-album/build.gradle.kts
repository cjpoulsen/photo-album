plugins {
    kotlin("jvm")
    application
}

dependencies {
    val kotestVersion: String by project
    val cliVersion: String by project

    implementation("com.github.ajalt.clikt:clikt:$cliVersion")

    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
}

application {
    mainClass.set("me.cpoulsen.photo.album.PhotoAlbumApplicationKt")
}

tasks.test {
    useJUnitPlatform()
}
