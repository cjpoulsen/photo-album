package me.cpoulsen.photo.album.core.services.implementation

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import me.cpoulsen.photo.album.core.repositories.abstraction.PhotoRepository
import me.cpoulsen.photo.album.models.Photo
import me.cpoulsen.photo.album.models.PhotoCollection

class PhotoServiceImplTests: DescribeSpec({
    lateinit var repository: PhotoRepository
    lateinit var photoService: PhotoServiceImpl

    lateinit var expectedCollection: PhotoCollection
    lateinit var actualCollection: PhotoCollection

    beforeContainer {
        repository = mockk()

        photoService = PhotoServiceImpl(repository)

        every { repository.getPhotos(any()) } returns PhotoCollection(emptyList())
        every { repository.getPhotos() } returns PhotoCollection(emptyList())
    }

    describe("getPhotos") {
        describe("with albumId") {
            expectedCollection = PhotoCollection(
                listOf(
                    Photo(10L, 1L, "My Photo", "www.photo.com", "www.thumbnail.com")
                )
            )

            every { repository.getPhotos(1L) } returns expectedCollection

            actualCollection = photoService.getPhotos(1L)

            it("calls photoRepository for photos within album") {
                verify { repository.getPhotos(1L) }

                actualCollection shouldBe expectedCollection
            }
        }

        describe("without albumId") {
            expectedCollection = PhotoCollection(
                listOf(
                    Photo(11L, 1L, "My Photo", "www.photo.com/11", "www.thumbnail.com/11"),
                    Photo(12L, 2L, "My Photo 2", "www.photo.com/12", "www.thumbnail.com/12")
                )
            )

            every { repository.getPhotos() } returns expectedCollection

            actualCollection = photoService.getPhotos()

            it("calls photoRepository for photo collection") {
                verify { repository.getPhotos() }

                actualCollection shouldBe expectedCollection
            }
        }
    }
})