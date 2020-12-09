data class Note(
    val id: Int,
    val userId: Int,
    val title: String,
    val text: String,
    val date: Int,
    val comments: List<Comment>?,
    val commentsQuantity: Int,
    val readComments: Int
)