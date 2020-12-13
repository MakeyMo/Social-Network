import Exceptions.CommentNotFoundException
import Exceptions.NoteNotFoundException

object NoteService {
    private val notes = mutableListOf<Note>()
    private val deletedNotes = mutableListOf<Note>()
    private val comments = mutableListOf<Comment>()
    private val deletedComments = mutableListOf<Comment>()
    private var lastNoteId = 0
    private var lastCommentId = 0

    fun addNote(note: Note): Note {
        val tmpNote = note.copy(id = lastNoteId + 1)
        lastNoteId += 1
        notes += tmpNote
        return notes.last()
    }

    fun deleteNote(note: Note): Note {
        if (notes.any{it.id == note.id}) {
            notes -= note
            deletedNotes += note
            return deletedNotes.last()
        } else {
            throw NoteNotFoundException("Такой заметки не существует")
        }
    }

    fun editNote(note: Note): Note {
        if (notes.any{it.id == note.id}) {
            val index = notes.indexOfFirst { it.id == note.id }
            val old = notes[index]
            notes[index] = note.copy(id = old.id, userId = old.userId)
            return notes[index]
        } else {
            throw NoteNotFoundException("Такой заметки не существует")
        }
    }

    fun getNoteList(): MutableList<Note> {
        return notes
    }

    fun getNoteById(noteId: Int): Note {
        if (notes.any{it.id == noteId}) {
            val index = notes.indexOfFirst { it.id == noteId }
            return notes[index]
        } else {
            throw NoteNotFoundException("Такой заметки не существует")
        }
    }

    fun createComment(comment: Comment): Comment {
        val tmpComment = comment.copy(id = lastCommentId + 1)
        lastCommentId += 1
        comments += tmpComment
        return comments.last()
    }

    fun deleteComment(comment: Comment): Comment {
        if (comments.any{it.id == comment.id}) {
            comments -= comment
            deletedComments += comment
            return deletedComments.last()
        } else {
            throw CommentNotFoundException("Такого комментария не существует")
        }
    }

    fun editComment(comment: Comment): Comment {
        if (comments.any{it.id == comment.id}) {
            val index = comments.indexOfFirst { it.id == comment.id }
            val old = comments[index]
            comments[index] = comment.copy(id = old.id, fromId = old.fromId, noteId = old.noteId)
            return comments[index]
        } else {
            throw CommentNotFoundException("Такого комментария не существует")
        }
    }

    fun getComments(note: Note): List<Comment> =
        comments.filter { it.noteId == note.id }.also{
            if (it.isEmpty()) throw CommentNotFoundException("Список комментариев пуст")
        }

    fun restoreComment(comment: Comment): Comment {
        if (deletedComments.any{it.id == comment.id}) {
            deletedComments -= comment
            comments += comment
            return comments.last()
        } else {
            throw CommentNotFoundException("Данный комментарий не был удален либо не существует")
        }
    }
}