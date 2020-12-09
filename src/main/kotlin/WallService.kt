import Exceptions.PostNotFoundException

object WallService {
    val posts = mutableListOf<Post>()
    val comments = mutableListOf<Comment>()

    fun addPost(post: Post): Post {
        posts += post
        return posts.last()
    }

    fun updatePost(post: Post): Boolean {
        val index = posts.indexOfFirst {it.id == post.id}.takeIf {it >= 0} ?: return false
        val old = posts[index]
        posts[index] = post.copy(ownerId = old.ownerId, date = old.date)
        return true
    }

    fun createComment(comment: Comment) {
        if(posts.any {it.id == comment.postId}) {
            comments += comment
        }
        else {
            throw PostNotFoundException("Пост с таким ID не найден")
        }
    }

    override fun toString(): String {
        for (post in posts) return "" +
                "\n${post.id}\n" +
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
                "${post.reposts}\n" +
                "${post.postType}\n" +
                "${post.signerId}\n" +
                "${post.canPin}\n" +
                "${post.canDelete}\n" +
                "${post.canEdit}\n" +
                "${post.isPinned}\n" +
                "${post.markedAsAdds}\n" +
                "${post.isFavorite}\n" +
                "${post.attachments}"
        return "лажа какая-то"
    }
}