package me.cpoulsen.photo.album.commands

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument

class PhotoAlbum: CliktCommand() {
    private val name by argument()

    override fun run() {
        echo("Hello $name!")
    }
}