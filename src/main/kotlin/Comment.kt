data class Comment(
        val id: Int,
        val fromId: Int,
        val postId: Int?,
        val noteId: Int?,
        val date: Int,
        val text: String,
        val replyToUser: Int,
        val replyToComment: Int,
        val attachments: Attachment?,
        val parentsStack: Array<Int>,
        val thread: CommentThread
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Comment

        if (id != other.id) return false
        if (fromId != other.fromId) return false
        if (date != other.date) return false
        if (text != other.text) return false
        if (replyToUser != other.replyToUser) return false
        if (replyToComment != other.replyToComment) return false
        if (attachments != other.attachments) return false
        if (!parentsStack.contentEquals(other.parentsStack)) return false
        if (thread != other.thread) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + fromId
        result = 31 * result + date
        result = 31 * result + text.hashCode()
        result = 31 * result + replyToUser
        result = 31 * result + replyToComment
        result = 31 * result + attachments.hashCode()
        result = 31 * result + parentsStack.contentHashCode()
        result = 31 * result + thread.hashCode()
        return result
    }
}