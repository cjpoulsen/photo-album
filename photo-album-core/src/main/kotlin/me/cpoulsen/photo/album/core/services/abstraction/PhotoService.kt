package me.cpoulsen.photo.album.core.services.abstraction

import me.cpoulsen.photo.album.models.Photo

interface PhotoService {
    fun getPhotos(albumId: Long? = null): List<Photo>
}