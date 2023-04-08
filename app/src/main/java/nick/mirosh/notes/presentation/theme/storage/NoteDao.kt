package nick.mirosh.notes.presentation.theme.storage

import nick.mirosh.notes.domain.Note

// NoteDao.kt
interface NoteDao {
    fun getAllNotes(): List<Note>

    fun insertOrUpdate(note: Note)

    fun delete(note: Note)

    fun getNoteById(id: Int): Note?
}