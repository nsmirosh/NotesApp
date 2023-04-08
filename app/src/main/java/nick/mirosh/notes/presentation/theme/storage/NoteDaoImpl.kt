package nick.mirosh.notes.presentation.theme.storage

import android.util.Log
import nick.mirosh.notes.data.di.annotations.MyCustomScope
import nick.mirosh.notes.domain.Note
import javax.inject.Inject

class NoteDaoImpl @Inject constructor() : NoteDao {

    private val notes = mutableListOf<Note>()
    private var nextId = 0

    override fun getAllNotes() = notes

    //in the notes list find the note with the same id
    //if it's found, remove it from the list and add the new note
    //if the note with the same id is not found, add it to the list
    override fun insertOrUpdate(note: Note) {
        val noteIndex = notes.indexOfFirst { it.id == note.id }
        if (noteIndex != -1) {
            notes.apply {
                removeAt(noteIndex)
                add(note)
            }
        } else {
            notes.add(note.copy(id = nextId++))
        }
        Log.d("NoteDaoImpl", "notes = $notes")
    }

    //remove the note from the list in case it exists
    override fun delete(note: Note) {
        notes.remove(note)
    }

    //find and return a note from the list by id
    override fun getNoteById(id: Int): Note? {
        return notes.find { it.id == id }
    }
}


/*
class NoteDaoImpl @Inject constructor(): NoteDao {
    override fun getAllNotes(): List<Note> {
        TODO("Not yet implemented")
    }

    override fun insertOrUpdate(note: Note) {
        TODO("Not yet implemented")
    }

    override fun delete(note: Note) {
        TODO("Not yet implemented")
    }
}*/
