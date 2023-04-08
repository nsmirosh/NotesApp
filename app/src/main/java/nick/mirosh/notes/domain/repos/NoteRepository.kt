package nick.mirosh.notes.domain.repos

import nick.mirosh.notes.domain.Note

// NoteRepository.kt
interface NoteRepository {
    suspend fun getAllNotes(): List<Note>

    suspend fun getNoteById(id: Int): Note?

    suspend fun saveOrUpdate(note: Note)

    suspend fun delete(note: Note)
}