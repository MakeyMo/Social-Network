import Exceptions.ChatMembershipException
import Exceptions.ChatNotFoundException
import Exceptions.MessageAuthorshipException
import Exceptions.MessageNotFoundException

object ChatService {

    private val chats = mutableListOf<Chat>()
    private val messages = mutableListOf<Message>()
    private var lastChatId = 0

    fun createChat(userId: Int, recipientId: Int, chat: Chat, message: Message): Chat {
        val tmpChat = chat.copy(id = lastChatId + 1, participantsId = mutableListOf(userId, recipientId))
        lastChatId += 1
        val tmpMessage = message.copy(chatId = chat.id)
        messages += tmpMessage
        val chatForAdd = tmpChat.copy(messages = mutableListOf(tmpMessage))
        chats += chatForAdd
        return chats.last()
    }

    fun deleteChat(userId: Int, chat: Chat) {
        if (chats.any {it.id !== chat.id}) {
            throw ChatNotFoundException("Такого чата не существует")
        }
        if (chat.participantsId.contains(userId)) {
            chats -= chat
            messages.forEach {
                if (it.chatId == chat.id) {
                    messages -= it
                }
            }
        }
    }

    fun getAllChatsForUser(userId: Int): List<Chat> {
        val userChats: MutableList<Chat> = emptyList<Chat>() as MutableList<Chat>
        chats.forEach {
            if (it.participantsId.contains(userId)) {
                userChats += it
            }
        }
        return userChats
    }

    fun getUnreadChatsCount(userId: Int): Int {
        var chatsCount = 0
        chats.forEach { chat ->
            if (chat.participantsId.contains(userId) && chat.messages.any{ !it.isRead })
               chatsCount += 1
        }
        return chatsCount
    }

    fun getSomeMessagesFromChat(userId: Int, chatId: Int, lastMessageFromTheEnd: Int, messagesQuantity: Int)
    : List<Message> {
        val chatMessages = emptyList<Message>().toMutableList()
        chats.forEach {
            if (it.id == chatId) {
                if (it.participantsId.contains(userId)) {
                    chatMessages += it.messages.slice(
                        it.messages.size - lastMessageFromTheEnd - 1..
                                it.messages.size - lastMessageFromTheEnd - 1 + messagesQuantity
                    )
                } else {
                    throw ChatMembershipException("Вы не являетесь аучастником данного чата")
                }
            }
            else {
                throw ChatNotFoundException("Такого чата не существует")
            }
        }
        return chatMessages
    }

    fun createMessage(userId: Int, message: Message): Message {
        val tmpMessage = message.copy(authorId = userId)
        messages += tmpMessage
        return messages.last()
    }

    fun deleteMessage(userId: Int, message: Message) {
        if (messages.any {it.chatId !== message.chatId}) {
            throw MessageNotFoundException("Такого сообщения не существует")
        }
        if (message.authorId == userId) {
            messages -= message
            chats.forEach {
                if (it.id == message.chatId && it.messages.isEmpty()) {
                    chats -= it
                }
            }
        } else {
            throw MessageAuthorshipException("Вы не являетесь автором данного сообщения")
        }
    }

    fun editMessage(userId: Int, message: Message): Message {
        if (message.authorId !== userId) {
            throw MessageAuthorshipException("Вы не являетесь автором данного сообщения")
        }
        if (messages.any {it.chatId !== message.chatId}) {
            throw MessageNotFoundException("Такого сообщения не существует")
        }
        else {
            val index = messages.indexOfFirst{it.chatId == message.chatId &&
                    it.authorId == message.authorId &&
            it.date == message.date}
            messages[index] = message.copy(chatId = message.chatId, authorId = message.authorId)
            return messages[index]
        }
    }


}