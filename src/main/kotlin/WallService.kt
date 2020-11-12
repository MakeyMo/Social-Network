object WallService {
    var posts = emptyArray<Post>()

    fun add(post: Post): Post {
        post.id = posts.size +1
        posts += post
        return posts.last()
    }

    fun update(post: Post): Boolean {
        val index = posts.indexOfFirst { it.id == post.id }.takeIf { it >= 0 } ?: return false
        val old = posts[index]
        posts[index] = post.copy(ownerId = old.ownerId, date = old.date)
        return true
    }

    override fun toString(): String {
        for (post in posts) return "\n\t\t\t${post.id}\n" +
                "            ${post.ownerId}\n" +
                "            ${post.fromId}\n" +
                "            ${post.createdBy}\n" +
                "            ${post.createdBy}\n" +
                "            ${post.text}\n" +
                "            ${post.replyOwnerId}\n" +
                "            ${post.replyPostId}\n" +
                "            ${post.friendsOnly}\n" +
                "            ${post.comments}\n" +
                "            ${post.likes}\n" +
                "            ${post.reposts})\n" +
                "            ${post.postType}\n" +
                "            ${post.signerId}\n" +
                "            ${post.canPin}\n" +
                "            ${post.canDelete}\n" +
                "            ${post.canEdit}\n" +
                "            ${post.isPinned}\n" +
                "            ${post.markedAsAdds}\n" +
                "            ${post.isFavorite}"
        return "лажа какая-то"
    }
}