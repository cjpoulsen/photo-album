package me.cpoulsen.photo.album.core.services.abstraction

import me.cpoulsen.photo.album.models.PhotoCollection

interface PhotoService {
    fun getPhotos(albumId: Long? = null): PhotoCollection
}