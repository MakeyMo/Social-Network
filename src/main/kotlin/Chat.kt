import java.util.*

data class Chat(
    val id: Int,
    val participantsId: MutableList<Int>,
    val creationDate: Calendar,
    val title: String,
    val messages: MutableList<Message>,
    val attachments: List<Attachment>?
)