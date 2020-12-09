import Exceptions.CommentNotFoundException
import Exceptions.NoteNotFoundException
import junit.framework.Assert.assertEquals
import org.junit.Assert
import org.junit.Test

class NoteServiceTest {

    @Test
    fun addNote() {
        val tmpNote = Note(
            123,
            321,
            "title",
            "text",
            101010,
            null,
            1,
            1
            )

        val result = NoteService.addNote(tmpNote)

        assertEquals(tmpNote, result)
    }

    @Test
    fun deleteExistingNote() {
        val tmpNote = Note(
            123,
            321,
            "title",
            "text",
            101010,
            null,
            1,
            1
        )
        NoteService.addNote(tmpNote)

        val result = NoteService.deleteNote(tmpNote)

        assertEquals(tmpNote, result)
    }

    @Test
    fun editNote() {
        val tmpNote1 = Note(
            123,
            321,
            "title",
            "text",
            101010,
            null,
            1,
            1
        )
        val tmpNote2 = Note(
            123,
            321,
            "title2",
            "text2",
            101010,
            null,
            1,
            1
        )
        NoteService.addNote(tmpNote1)

        val result = NoteService.editNote(tmpNote2)

        assertEquals(tmpNote2, result)
    }

    @Test
    fun getNoteList() {
        val tmpNote = Note(
            123,
            321,
            "title",
            "text",
            101010,
            null,
            1,
            1
        )
        val tmpNoteList = mutableListOf<Note>(tmpNote)
        NoteService.addNote(tmpNote)

        val result = NoteService.getNoteList()

        assertEquals(tmpNoteList, result)
    }

    @Test
    fun getNoteById() {
        val tmpNote = Note(
            123,
            321,
            "title",
            "text",
            101010,
            null,
            1,
            1
        )
        NoteService.addNote(tmpNote)

        val result = NoteService.getNoteById(tmpNote.id)

        assertEquals(tmpNote, result)
    }

    @Test
    fun createComment() {
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

        val result = NoteService.createComment(tmpComment)

        assertEquals(tmpComment, result)
    }

    @Test
    fun deleteComment() {
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
        NoteService.createComment(tmpComment)

        val result = NoteService.deleteComment(tmpComment)

        assertEquals(tmpComment, result)
    }

    @Test
    fun editComment() {
        val tmpComment1 = Comment(
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
        val tmpComment2 = Comment(
            987,
            789,
            123,
            323,
            101010,
            "!",
            654,
            456,
            null,
            emptyArray(),
            CommentThread(1, emptyArray(), true, true)
        )
        NoteService.createComment(tmpComment1)

        val result = NoteService.editComment(tmpComment2)

        assertEquals(tmpComment2, result)
    }

    @Test
    fun getComments() {
        val tmpNote = Note(
            123,
            321,
            "title",
            "text",
            101010,
            null,
            1,
            1
        )
        val tmpComment = Comment(
            987,
            789,
            321,
            123,
            121212,
            "yo",
            654,
            456,
            null,
            emptyArray(),
            CommentThread(1, emptyArray(), true, true)
        )
        val tmpCommentsOfNote = listOf<Comment>(tmpComment)
        NoteService.addNote(tmpNote)
        NoteService.createComment(tmpComment)

        val result = NoteService.getComments(tmpNote)

        assertEquals(tmpCommentsOfNote, result)
    }

    @Test
    fun restoreComment() {
        val tmpComment = Comment(
            987,
            789,
            321,
            123,
            121212,
            "yo",
            654,
            456,
            null,
            emptyArray(),
            CommentThread(1, emptyArray(), true, true)
        )
        NoteService.createComment(tmpComment)
        NoteService.deleteComment(tmpComment)

        val result = NoteService.restoreComment(tmpComment)

        assertEquals(tmpComment, result)
    }

    @Test(expected = NoteNotFoundException::class)
    fun shouldThrow() {
        val tmpNote = Note(
            123,
            321,
            "title",
            "text",
            101010,
            null,
            1,
            1
        )

        NoteService.getNoteById(tmpNote.id)
    }

    @Test(expected = CommentNotFoundException::class)
    fun mustThrow() {
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

        NoteService.deleteComment(tmpComment)
    }
}