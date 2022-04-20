package me.cpoulsen.photo.album.core.repositories.abstraction

import me.cpoulsen.photo.album.models.PhotoCollection

interface PhotoRepository {
    fun getPhotos(): PhotoCollection

    fun getPhotos(albumId: Long): PhotoCollection
}