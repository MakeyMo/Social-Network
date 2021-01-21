package ru.netology

import Comment
import CommentThread
import Exceptions.PostNotFoundException
import Post
import WallService
import org.junit.Test

import org.junit.Assert.*

class WallServiceTest {

    @Test
    fun add() {
        val tmpPost = Post(
            123,
            321,
            456,
            654,
            121212,
            "Первый!",
            789,
            987,
            false,
            null,
            null,
            null,
            "!",
            0,
            true,
            true,
            true,
            false,
            false,
            false,
            null
        )

        val result = WallService.addPost(tmpPost).id

        assertEquals(123, result)
    }

    @Test
    fun updateForExistingPost() {
        val tmpPost = Post(
            123,
            321,
            456,
            654,
            121212,
            "Первый!",
            789,
            987,
            false,
            null,
            null,
            null,
            "!",
            0,
            true,
            true,
            true,
            false,
            false,
            false,
            null
        )

        WallService.addPost(tmpPost)

        val result: Boolean = WallService.updatePost(tmpPost)

        assertEquals(true, result)
    }

    @Test
    fun updateForNotExistingPost() {
        val tmpPost = Post(
            123,
            321,
            456,
            654,
            121212,
            "Первый!",
            789,
            987,
            false,
            null,
            null,
            null,
            "!",
            0,
            true,
            true,
            true,
            false,
            false,
            false,
            null
        )

        val result: Boolean = WallService.updatePost(tmpPost)

        assertEquals(false, result)
    }

    @Test
    fun createCommentSuccess() {
        val tmpPost = Post(
            123,
            321,
            456,
            654,
            121212,
            "Первый!",
            789,
            987,
            false,
            null,
            null,
            null,
            "!",
            0,
            true,
            true,
            true,
            false,
            false,
            false,
            null
        )
        val tmpComment = Comment(
            987,
            789,
            123,
            323,
            121212,
            "yo",
            654,
            456,
            null,
            emptyArray(),
            CommentThread(1, emptyArray(), true, true)
        )

        WallService.addPost(tmpPost)
        WallService.createComment(tmpComment)
        val result = WallService.posts.any { it.id == tmpComment.postId }

        assertEquals(true, result)
    }

    @Test(expected = PostNotFoundException::class)
    fun shouldThrow() {
        val tmpPost = Post(
            124,
            321,
            456,
            654,
            121212,
            "Первый!",
            789,
            987,
            false,
            null,
            null,
            null,
            "!",
            0,
            true,
            true,
            true,
            false,
            false,
            false,
            null
        )
        val tmpComment = Comment(
            987,
            789,
            123,
            323,
            121212,
            "yo",
            654,
            456,
            null,
            emptyArray(),
            CommentThread(1, emptyArray(), true, true)
        )

        WallService.addPost(tmpPost)
        WallService.createComment(tmpComment)
    }
}