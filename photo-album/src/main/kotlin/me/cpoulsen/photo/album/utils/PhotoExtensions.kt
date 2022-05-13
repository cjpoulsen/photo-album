package me.cpoulsen.photo.album.utils

import me.cpoulsen.photo.album.models.Photo

fun List<Photo>.toConsoleOutput() = this.map {
    "[${it.id}] ${it.title}"
}
