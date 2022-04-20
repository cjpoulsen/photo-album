package me.cpoulsen.photo.album.models

import kotlinx.serialization.Serializable

@Serializable
data class Photo(
    val id: Long,
    val albumId: Long,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)