package me.cpoulsen.photo.album.repositories.implementation

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.runBlocking
import me.cpoulsen.photo.album.core.repositories.abstraction.PhotoRepository
import me.cpoulsen.photo.album.models.Photo

class PhotoAlbumRepository(private val httpClient: HttpClient, private val baseUrl: String): PhotoRepository {
    private val albumIdParameter = "albumId"
    private val photosEndpoint = "/photos"

    override fun getPhotos(): List<Photo> {
        val collection: List<Photo> = runBlocking {
            httpClient.get("$baseUrl$photosEndpoint").body()
        }

        return collection
    }

    override fun getPhotos(albumId: Long): List<Photo> {
        val collection: List<Photo> = runBlocking {
            httpClient.get("$baseUrl$photosEndpoint") {
                parameter(albumIdParameter, albumId)
            }.body()
        }

        return collection
    }
}