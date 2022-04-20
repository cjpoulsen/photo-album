package me.cpoulsen.photo.album.core.repositories.abstraction

import me.cpoulsen.photo.album.models.Photo
import me.cpoulsen.photo.album.models.PhotoCollection

interface PhotoRepository {
    fun getPhotos(): List<Photo>

    fun getPhotos(albumId: Long): List<Photo>
}