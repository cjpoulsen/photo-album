package me.cpoulsen.photo.album.commands

import com.github.ajalt.clikt.core.BadParameterValue
import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock.equalTo
import com.github.tomakehurst.wiremock.client.WireMock.get
import com.github.tomakehurst.wiremock.client.WireMock.ok
import com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo
import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.assertions.withClue
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.extensions.wiremock.ListenerMode
import io.kotest.extensions.wiremock.WireMockListener
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import me.cpoulsen.photo.album.ALBUM_ID_QUERY_PARAM
import me.cpoulsen.photo.album.client.photoAlbumClient
import me.cpoulsen.photo.album.core.services.implementation.PhotoServiceImpl
import me.cpoulsen.photo.album.models.Photo
import me.cpoulsen.photo.album.repositories.implementation.PhotoAlbumRepository

class PhotoAlbumTests: DescribeSpec({
    lateinit var command: PhotoAlbum

    val url = "http://localhost:8080"

    val contentTypeHeader = "Content-Type"
    val contentTypeJson = "application/json"

    val photoAlbumServer = WireMockServer(8080)
    val client = photoAlbumClient()

    val firstAlbum = listOf(
        Photo(1, 1, "first", "url", "thumbnail"),
        Photo(2, 1, "second", "url", "thumbnail"),
    )

    val secondAlbum = listOf(
        Photo(3, 2, "third", "url", "thumbnail")
    )

    val thirdAlbum = listOf(
        Photo(4, 3, "fourth", "url", "thumbnail")
    )

    val allPhotos = firstAlbum + secondAlbum + thirdAlbum

    listener(WireMockListener(photoAlbumServer, ListenerMode.PER_TEST))

    beforeTest {
        val repository = PhotoAlbumRepository(client, url)
        val service = PhotoServiceImpl(repository)

        command = PhotoAlbum(service)
    }

    describe("photo-album") {

        describe("with albumId") {
            photoAlbumServer.stubFor(
                get(urlPathEqualTo("/photos"))
                    .withQueryParam(ALBUM_ID_QUERY_PARAM, equalTo("3"))
                    // .withHeader(contentTypeHeader, equalTo(contentTypeJson))
                    .willReturn(
                        ok()
                            .withBody(Json.encodeToString(thirdAlbum))
                            .withHeader(contentTypeHeader, contentTypeJson)
                    )
            )

            it("should return filtered photos") {
                withClue("should parse and run the command") {
                    shouldNotThrow<Exception> {
                        command.parse(listOf("3"))
                    }
                }
            }

            describe("that is not a number") {
                it("should throw error") {
                    shouldThrow<BadParameterValue> {
                        command.parse(listOf("asdf"))
                    }
                }
            }
        }

        describe("without albumId") {

            photoAlbumServer.stubFor(
                get(urlPathEqualTo("/photos"))
                    .willReturn(
                        ok()
                            .withBody(Json.encodeToString(allPhotos))
                            .withHeader(contentTypeHeader, contentTypeJson)
                    )
            )

            it("should return all photos") {

                withClue("should parse and run the command") {
                    shouldNotThrow<Exception> {
                        command.parse(emptyList())
                    }
                }
            }
        }
    }
})