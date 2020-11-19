object WallService {
    val posts = mutableListOf<Post>()

    fun add(post: Post): Post {
        posts += post
        return posts.last()
    }

    fun update(post: Post): Boolean {
        val index = posts.indexOfFirst {it.id == post.id}.takeIf {it >= 0} ?: return false
        val old = posts[index]
        posts[index] = Post(
                post.id,
                old.ownerId,
                old.date,
                post.createdBy,
                post.date,
                post.text,
                post.replyOwnerId,
                post.replyPostId,
                post.friendsOnly,
                post.comments,
                post.likes,
                post.reposts,
                post.postType,
                post.signerId,
                post.canPin,
                post.canDelete,
                post.canEdit,
                post.isPinned,
                post.markedAsAdds,
                post.isFavorite
        )
        return true
    }

    override fun toString(): String {
        for (post in posts) return "\n${post.id}\n" +
                "${post.ownerId}\n" +
                "${post.fromId}\n" +
                "${post.createdBy}\n" +
                "${post.date}\n" +
                "${post.text}\n" +
                "${post.replyOwnerId}\n" +
                "${post.replyPostId}\n" +
                "${post.friendsOnly}\n" +
                "${post.comments}\n" +
                "${post.likes}\n" +
                "${post.reposts})\n" +
                "${post.postType}\n" +
                "${post.signerId}\n" +
                "${post.canPin}\n" +
                "${post.canDelete}\n" +
                "${post.canEdit}\n" +
                "${post.isPinned}\n" +
                "${post.markedAsAdds}\n" +
                "${post.isFavorite}"
        return "лажа какая-то"
    }
}