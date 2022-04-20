package me.cpoulsen.photo.album.models

import kotlinx.serialization.Serializable

@Serializable
class PhotoCollection(
    val photos: List<Photo>
)