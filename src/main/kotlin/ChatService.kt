import Exceptions.ChatMembershipException
import Exceptions.ChatNotFoundException
import Exceptions.MessageAuthorshipException
import Exceptions.MessageNotFoundException

object ChatService {

    private val chats = mutableListOf<Chat>()
    private val messages = mutableListOf<Message>()
    private var lastChatId = 0

    fun clearChatService() {
        chats.clear()
        messages.clear()
    }

    fun createChat(userId: Int, recipientId: Int, chat: Chat): Chat {
        val tmpChat = chat.copy(id = lastChatId + 1, participantsId = mutableListOf(userId, recipientId))
        lastChatId + 1
        chats += tmpChat
        return chats.last()
    }

    fun deleteChat(userId: Int, chat: Chat) {
        if (chats.none { it.id == chat.id }) {
            throw ChatNotFoundException("Такого чата не существует")
        }
        if (chat.participantsId.contains(userId)) {
            chats -= chat
            messages.removeAll { it.chatId == chat.id }
        } else {
            throw ChatMembershipException("Вы не являетесь участником данного чата")
        }
    }

    fun getAllChatsForUser(userId: Int): List<Chat> =
        chats.asSequence()
            .filter { it.participantsId.contains(userId) }
            .ifEmpty { throw ChatNotFoundException("У вас нет ни одного чата") }
            .toList()


    fun getUnreadChatsCount(userId: Int): Int =
        chats.asSequence()
            .filter { chat ->
                chat.participantsId.contains(userId) && chat.messages.any { !it.isRead } }
            .ifEmpty { throw ChatNotFoundException("Не прочитанных чатов нет") }
            .count()

    fun createMessage(userId: Int, recipientId: Int, chat: Chat, message: Message): Message {
        if (chats.none { it.id == chat.id }) {
            createChat(userId, recipientId, chat)
        }
        val tmpMessage = message.copy(authorId = userId, chatId = chat.id)
        messages += tmpMessage
        return messages.last()
    }


    fun deleteMessage(userId: Int, message: Message) {
        if (messages.none { it.chatId == message.chatId }) {
            throw MessageNotFoundException("Такого сообщения не существует")
        }
        if (message.authorId == userId) {
            messages -= message
            chats.forEach {
                if (it.id == message.chatId && it.messages.size == 1) {
                    chats -= it
                }
            }
        } else {
            throw MessageAuthorshipException("Вы не являетесь автором данного сообщения")
        }
    }

    fun editMessage(userId: Int, message: Message): Message {
        if (message.authorId != userId) {
            throw MessageAuthorshipException("Вы не являетесь автором данного сообщения")
        }
        if (messages.any { it.chatId != message.chatId }) {
            throw MessageNotFoundException("Такого сообщения не существует")
        } else {
            val index = messages.indexOfFirst {
                it.chatId == message.chatId &&
                        it.authorId == message.authorId &&
                        it.date == message.date
            }
            messages[index] = message.copy(chatId = message.chatId, authorId = message.authorId)
            return messages[index]
        }
    }


    fun getSomeMessagesFromChat(userId: Int, chatId: Int, lastMessageFromTheEnd: Int, messagesQuantity: Int)
            : List<Message> =
        chats.asSequence()
            .filter { it.id == chatId }
            .ifEmpty { throw ChatNotFoundException("Такого чата не существует") }
            .filter { userId in it.participantsId }
            .ifEmpty { throw ChatMembershipException("Вы не являетесь участником данного чата") }
            .map {
                with(it.messages) {
                    slice(
                        size - lastMessageFromTheEnd until size - lastMessageFromTheEnd + messagesQuantity
                    )
                }
            }
            .flatten()
            .toList()
}