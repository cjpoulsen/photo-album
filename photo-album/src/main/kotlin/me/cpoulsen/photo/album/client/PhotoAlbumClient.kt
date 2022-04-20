package me.cpoulsen.photo.album.client

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json

fun photoAlbumClient() = HttpClient {
    install(ContentNegotiation) {
        json()
    }
}
