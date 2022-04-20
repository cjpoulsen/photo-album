package me.cpoulsen.photo.album.models

import kotlinx.serialization.Serializable

@Serializable
data class PhotoCollection(
    val photos: List<Photo>
)