package me.cpoulsen.photo.album.commands

import com.github.ajalt.clikt.core.BadParameterValue
import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.arguments.optional
import com.github.ajalt.clikt.parameters.types.long
import me.cpoulsen.photo.album.ALBUM_ID_GREATER_THAN_ZERO_MESSAGE
import me.cpoulsen.photo.album.core.services.abstraction.PhotoService
import me.cpoulsen.photo.album.utils.toConsoleOutput

class PhotoAlbum(private val service: PhotoService): CliktCommand() {
    private val albumId by argument(help = "albumId must be a positive integer.").long().optional()

    override fun run() {
        val albumIdNumber = albumId

        if (albumIdNumber != null && albumIdNumber < 0) throw BadParameterValue(ALBUM_ID_GREATER_THAN_ZERO_MESSAGE)

        val photos = service.getPhotos(albumId)

        val output = photos.toConsoleOutput()

        echoPhotoOutput(output)
    }

    private fun echoPhotoOutput(photos: List<String>) {
        photos.forEach { echo(it) }
    }
}