package ru.netology

import Chat
import ChatService.chats
import ChatService.messages
import Message
import org.junit.Test

import org.junit.Assert.*

class ChatServiceTest {

    @Test
    fun createChat() {
        val userId = 100
        val recipientId = 200
        val chatId = 1
        val tmpMessage = Message(chatId,
            userId,
            java.util.Calendar.getInstance(),
        "text",
        false,
        null)
        val tmpChat = Chat(chatId,
            mutableListOf(),
            java.util.Calendar.getInstance(),
            "tmpChat",
            mutableListOf(tmpMessage),
            null)

        val result = ChatService.createChat(userId, recipientId, tmpChat)

        assertEquals(tmpChat.copy(participantsId = mutableListOf(100, 200)), result)
    }

    @Test
    fun deleteChat() {
        val userId = 100
        val recipientId = 200
        val chatId = 1
        val tmpMessage = Message(chatId,
            userId,
            java.util.Calendar.getInstance(),
            "text",
            false,
            null)
        val tmpChat = Chat(chatId,
            mutableListOf(),
            java.util.Calendar.getInstance(),
            "tmpChat",
            mutableListOf(tmpMessage),
            null)
        val newChat = ChatService.createChat(userId, recipientId, tmpChat)

        ChatService.deleteChat(userId, newChat)
        val result = chats

        assertEquals(mutableListOf<Chat>(), result)
    }

    @Test
    fun getAllChatsForUser() {
        val userId = 100
        val recipientId = 200
        val chatId = 1
        val tmpMessage = Message(chatId,
            userId,
            java.util.Calendar.getInstance(),
            "text",
            false,
            null)
        val tmpChat = Chat(chatId,
            mutableListOf(),
            java.util.Calendar.getInstance(),
            "tmpChat",
            mutableListOf(tmpMessage),
            null)
        val newChat = ChatService.createChat(userId, recipientId, tmpChat)

        val result = ChatService.getAllChatsForUser(userId)

        assertEquals(listOf(newChat), result)
    }

    @Test
    fun getUnreadChatsCount() {
        val userId = 100
        val recipientId = 200
        val chatId = 1
        val tmpMessage = Message(chatId,
            userId,
            java.util.Calendar.getInstance(),
            "text",
            false,
            null)
        val tmpChat = Chat(chatId,
            mutableListOf(userId, recipientId),
            java.util.Calendar.getInstance(),
            "tmpChat",
            mutableListOf(tmpMessage),
            null)
        ChatService.createMessage(userId, recipientId, tmpChat, tmpMessage)

        val result = ChatService.getUnreadChatsCount(userId)

        assertEquals(1, result)
    }

    @Test
    fun createMessage() {
        val chatId = 1
        val userId = 100
        val recipientId = 200
        val text = "Text"
        val tmpMessage = Message(
            chatId,
            userId,
            java.util.Calendar.getInstance(),
            text,
            false,
            null)
        val tmpChat = Chat(chatId,
            mutableListOf(),
            java.util.Calendar.getInstance(),
            "tmpChat",
            mutableListOf(tmpMessage),
            null)

        val result = ChatService.createMessage(userId, recipientId, tmpChat, tmpMessage)

        assertEquals(tmpMessage, result)
    }


    @Test
    fun deleteMessage() {
        val chatId = 1
        val userId = 100
        val recipientId = 200
        val text = "Text"
        val tmpMessage = Message(
            chatId,
            userId,
            java.util.Calendar.getInstance(),
            text,
            false,
            null)
        val tmpChat = Chat(chatId,
            mutableListOf(),
            java.util.Calendar.getInstance(),
            "tmpChat",
            mutableListOf(tmpMessage),
            null)
        ChatService.createMessage(userId, recipientId, tmpChat, tmpMessage)

        ChatService.deleteMessage(userId, tmpMessage)
        val result = messages

        assertEquals(mutableListOf<Message>(), result)
    }

    @Test
    fun editMessage() {
        val chatId = 1
        val userId = 100
        val recipientId = 200
        val text = "Text"
        val tmpMessage = Message(
            chatId,
            userId,
            java.util.Calendar.getInstance(),
            text,
            false,
            null)
        val tmpChat = Chat(chatId,
            mutableListOf(),
            java.util.Calendar.getInstance(),
            "tmpChat",
            mutableListOf(tmpMessage),
            null)
        ChatService.createMessage(userId, recipientId, tmpChat, tmpMessage)

        val result = ChatService.editMessage(userId, tmpMessage)

        assertEquals(tmpMessage, result)
    }

    @Test
    fun getSomeMessagesFromChat() {
        val userId = 100
        val recipientId = 200
        val chatId = 1
        val tmpMessage1 = Message(chatId,
            userId,
            java.util.Calendar.getInstance(),
            "firstMessage",
            false,
            null)
        val tmpMessage2 = Message(chatId,
            userId,
            java.util.Calendar.getInstance(),
            "secondMessage",
            false,
            null)
        val tmpChat = Chat(chatId,
            mutableListOf(),
            java.util.Calendar.getInstance(),
            "tmpChat",
            mutableListOf(tmpMessage1, tmpMessage2),
            null)
        ChatService.createMessage(userId, recipientId, tmpChat, tmpMessage1)
        ChatService.createMessage(userId, recipientId, tmpChat, tmpMessage2)
        ChatService.createChat(userId, recipientId, tmpChat)

        val result = ChatService.getSomeMessagesFromChat(userId, chatId, 2, 1)

        assertEquals(listOf(tmpMessage1), result)
    }
}