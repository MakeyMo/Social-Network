import java.util.*

data class Message(
    val chatId: Int,
    val authorId: Int,
    val date: Calendar,
    val text: String,
    val isRead: Boolean,
    val attachments: List<Attachment>?
)