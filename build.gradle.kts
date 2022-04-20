plugins {
    kotlin("jvm") version "1.6.20" apply false
    kotlin("plugin.serialization") version "1.6.10" apply false
}

allprojects {
    group = "me.cpoulsen"
    version = "0.1.0"

    repositories {
        mavenCentral()
    }
}
