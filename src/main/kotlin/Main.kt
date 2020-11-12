fun main() {

    val firstPost: Post = Post(
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

    WallService.add(firstPost)

    println(WallService)

    WallService.update(firstPost)

    println(WallService)
}