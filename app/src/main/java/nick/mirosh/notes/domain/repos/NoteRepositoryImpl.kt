package nick.mirosh.notes.domain.repos

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import nick.mirosh.notes.domain.Note
import nick.mirosh.notes.presentation.theme.storage.NoteDao
import javax.inject.Inject

// NoteRepository.kt
class NoteRepositoryImpl @Inject constructor(private val noteDao: NoteDao) : NoteRepository {
    override suspend fun getAllNotes() = withContext(Dispatchers.Main) {
        Log.d("NoteRepository", "noteDao instance = ${noteDao.hashCode()}")
        noteDao.getAllNotes()
    }

    override suspend fun getNoteById(id: Int): Note? {
        return withContext(Dispatchers.Main) {
            noteDao.getNoteById(id)
        }
    }

    override suspend fun saveOrUpdate(note: Note) {
        withContext(Dispatchers.Main) {
            Log.d("NoteRepository", "noteDao instance = ${noteDao.hashCode()}")
            noteDao.insertOrUpdate(note)
        }
    }

    override suspend fun delete(note: Note) {
        withContext(Dispatchers.Main) {
            noteDao.delete(note)
        }
    }
}