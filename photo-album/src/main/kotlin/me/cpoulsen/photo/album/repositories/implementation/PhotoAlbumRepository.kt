package me.cpoulsen.photo.album.repositories.implementation

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.runBlocking
import me.cpoulsen.photo.album.ALBUM_ID_QUERY_PARAM
import me.cpoulsen.photo.album.PHOTOS_ENDPOINT
import me.cpoulsen.photo.album.core.repositories.abstraction.PhotoRepository
import me.cpoulsen.photo.album.models.Photo

class PhotoAlbumRepository(private val httpClient: HttpClient, private val baseUrl: String): PhotoRepository {


    override fun getPhotos(): List<Photo> {
        val collection: List<Photo> = runBlocking {
            httpClient.get("$baseUrl$PHOTOS_ENDPOINT").body()
        }

        return collection
    }

    override fun getPhotos(albumId: Long): List<Photo> {
        val collection: List<Photo> = runBlocking {
            httpClient.get("$baseUrl$PHOTOS_ENDPOINT") {
                parameter(ALBUM_ID_QUERY_PARAM, albumId)
            }.body()
        }

        return collection
    }
}