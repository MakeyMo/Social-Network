import org.junit.Test

import org.junit.Assert.*

class WallServiceTest {

    @Test
    fun add() {
        val tmpPost = Post(
            123,
            321,
            456,
            654,
            121212,
            "Первый!",
            789,
            987,
            false,
            Comment(1, true, true),
            Like(0, true, true, true),
            Repost(0, false),
            "!",
            0,
            true,
            true,
            true,
            false,
            false,
            false)

        val result = WallService.add(tmpPost).id

        assertEquals(1, result)
    }

    @Test
    fun updateForExistingPost() {
        val tmpPost = Post(
            123,
            321,
            456,
            654,
            121212,
            "Первый!",
            789,
            987,
            false,
            Comment(1, true, true),
            Like(0, true, true, true),
            Repost(0, false),
            "!",
            0,
            true,
            true,
            true,
            false,
            false,
            false)

        WallService.add(tmpPost)

        val result: Boolean = WallService.update(tmpPost)

        assertEquals(true, result)
    }

    @Test
    fun updateForNotExistingPost() {
        val tmpPost = Post(
            123,
            321,
            456,
            654,
            121212,
            "Первый!",
            789,
            987,
            false,
            Comment(1, true, true),
            Like(0, true, true, true),
            Repost(0, false),
            "!",
            0,
            true,
            true,
            true,
            false,
            false,
            false)

        val result: Boolean = WallService.update(tmpPost)

        assertEquals(false, result)
    }
}