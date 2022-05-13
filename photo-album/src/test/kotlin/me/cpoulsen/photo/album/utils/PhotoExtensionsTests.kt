package me.cpoulsen.photo.album.utils

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import me.cpoulsen.photo.album.models.Photo

class PhotoExtensionsTests: DescribeSpec({
    describe("List<Photo>.toConsoleOutput()") {
        it("should format photo with id and title") {
            val expected = listOf(
                "[1] foo",
                "[2] foo bar"
            )

            val photos = listOf(
                Photo(1, 1, "foo", "url", "thumbnail"),
                Photo(2, 1, "foo bar", "url2", "thumbnail2")
            )

            val actual = photos.toConsoleOutput()

            actual shouldBe expected
        }
    }
})