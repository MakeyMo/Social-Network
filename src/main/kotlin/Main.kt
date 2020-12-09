fun main() {

    val firstPost = Post(
            123,
            321,
            456,
            654,
            121212,
            "Первый!",
            789,
            987,
            false,
            null,
            null,
            null,
            "!",
            0,
            true,
            true,
            true,
            false,
            false,
            false,
            null
    )

    WallService.addPost(firstPost)

    println(WallService)

    WallService.updatePost(firstPost)

    println(WallService)
}