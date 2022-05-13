package me.cpoulsen.photo.album

import me.cpoulsen.photo.album.client.photoAlbumClient
import me.cpoulsen.photo.album.commands.PhotoAlbum
import me.cpoulsen.photo.album.core.services.implementation.PhotoServiceImpl
import me.cpoulsen.photo.album.repositories.implementation.PhotoAlbumRepository

fun main(args: Array<String>) {
    val client = photoAlbumClient()
    val photoRepository = PhotoAlbumRepository(client, BASE_URL)
    val photoService = PhotoServiceImpl(photoRepository)

    PhotoAlbum(photoService).main(args)

    client.close()
}