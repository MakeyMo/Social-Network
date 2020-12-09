import Exceptions.CommentNotFoundException
import Exceptions.NoteNotFoundException

object NoteService {
    val notes = mutableListOf<Note>()
    val deletedNotes = mutableListOf<Note>()
    val comments = mutableListOf<Comment>()
    val deletedComments = mutableListOf<Comment>()

    fun addNote(note: Note): Note {
        notes += note
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
        comments += comment
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
            if (it.isEmpty()) throw NoteNotFoundException("Такой заметки не существует")
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