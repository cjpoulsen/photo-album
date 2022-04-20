package me.cpoulsen.photo.album.repositories.implementation

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import me.cpoulsen.photo.album.models.Photo

class PhotoAlbumRepositoryTests: DescribeSpec({
    lateinit var photoAlbumRepository: PhotoAlbumRepository

    lateinit var actual: List<Photo>
    lateinit var expected: List<Photo>

    val firstPhoto = Photo(1L, 1L, "First Photo", "www.photos.com/photos/1", "www.photos.com/thumbnails/1")
    val secondPhoto = Photo(2L, 2L, "Second Photo", "www.photos.com/photos/2", "www.photos.com/thumbnails/2")

    val filteredPhotos = listOf(
        firstPhoto
    )

    val allPhotos = listOf(
        firstPhoto,
        secondPhoto
    )

    fun setupMockEngine() = MockEngine { request ->
        val content = if (request.method == HttpMethod.Get && request.url.parameters["albumId"] == "1") {
            filteredPhotos
        } else if (request.method == HttpMethod.Get) {
            allPhotos
        } else throw RuntimeException("Unexpected mock request.")

        respond(content = Json.encodeToString(content), headers = headersOf(HttpHeaders.ContentType, "application/json"))
    }

    fun setupClient(mockEngine: MockEngine) = HttpClient(mockEngine) {
        install(ContentNegotiation) {
            json()
        }
    }

    beforeEach {
        val mockEngine = setupMockEngine()

        val client = setupClient(mockEngine)

        photoAlbumRepository = PhotoAlbumRepository(client, "www.testUrl.com")
    }

    describe("getPhotos()") {
        it("should call to httpClient to get all photos") {
            actual = photoAlbumRepository.getPhotos()

            actual shouldBe allPhotos
        }
    }

    describe("getPhotos(albumId)") {
        it("should call to httpClient to get photos filtered by albumId") {
            actual = photoAlbumRepository.getPhotos(1)

            actual shouldBe filteredPhotos
        }
    }
})

