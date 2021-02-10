package ru.netology

import Chat
import Exceptions.ChatMembershipException
import Exceptions.ChatNotFoundException
import Exceptions.MessageAuthorshipException
import Exceptions.MessageNotFoundException
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
    fun getAllChatsForUser() {
        ChatService.clearChatService()

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
        ChatService.clearChatService()

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
        ChatService.clearChatService()

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
    fun editMessage() {
        ChatService.clearChatService()

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
        ChatService.clearChatService()

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

        val result = ChatService.getSomeMessagesFromChat(userId, chatId, 2, 1)

        assertEquals(listOf(tmpMessage1), result)
    }

    @Test(expected = ChatMembershipException::class)
    fun mustThrow() {
        ChatService.clearChatService()

        val chatId = 1
        val userId = 100
        val recipientId = 200
        val anotherUserId = 300
        val text = "Text"
        val tmpMessage = Message(
            chatId,
            userId,
            java.util.Calendar.getInstance(),
            text,
            false,
            null)
        val tmpChat = Chat(chatId,
            mutableListOf(userId, recipientId),
            java.util.Calendar.getInstance(),
            "tmpChat",
            mutableListOf(tmpMessage),
            null)

        ChatService.createMessage(userId, recipientId, tmpChat, tmpMessage)

        ChatService.deleteChat(anotherUserId, tmpChat)
    }

    @Test(expected = MessageAuthorshipException::class)
    fun shouldThrow() {
        ChatService.clearChatService()

        val chatId = 1
        val userId = 100
        val recipientId = 200
        val anotherUserId = 300
        val text = "Text"
        val tmpMessage = Message(
            chatId,
            userId,
            java.util.Calendar.getInstance(),
            text,
            false,
            null)
        val tmpChat = Chat(chatId,
            mutableListOf(userId, recipientId),
            java.util.Calendar.getInstance(),
            "tmpChat",
            mutableListOf(tmpMessage),
            null)

        ChatService.createMessage(userId, recipientId, tmpChat, tmpMessage)

        ChatService.deleteMessage(anotherUserId, tmpMessage)
    }

    @Test(expected = ChatNotFoundException::class)
    fun willThrow() {
        ChatService.clearChatService()

        val chatId = 1
        val userId = 100
        val recipientId = 200
        val tmpChat = Chat(chatId,
            mutableListOf(userId, recipientId),
            java.util.Calendar.getInstance(),
            "tmpChat",
            mutableListOf(),
            null)

        ChatService.deleteChat(userId, tmpChat)
    }

    @Test(expected = MessageNotFoundException::class)
    fun isThrow() {
        ChatService.clearChatService()

        val chatId = 1
        val userId = 100
        val text = "Text"
        val tmpMessage = Message(
            chatId,
            userId,
            java.util.Calendar.getInstance(),
            text,
            false,
            null)

        ChatService.deleteMessage(userId, tmpMessage)
    }
}