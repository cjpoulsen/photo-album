package me.cpoulsen.photo.album.core.services.implementation

import me.cpoulsen.photo.album.core.repositories.abstraction.PhotoRepository
import me.cpoulsen.photo.album.core.services.abstraction.PhotoService
import me.cpoulsen.photo.album.models.PhotoCollection

class PhotoServiceImpl(private val repository: PhotoRepository): PhotoService {
    override fun getPhotos(albumId: Long?): PhotoCollection {
        return if (albumId == null) {
            repository.getPhotos()
        } else repository.getPhotos(albumId)
    }
}