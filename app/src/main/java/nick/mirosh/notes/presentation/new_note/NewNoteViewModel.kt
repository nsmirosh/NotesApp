package nick.mirosh.notes.presentation.new_note

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import nick.mirosh.notes.data.di.annotations.MyCustomScope
import nick.mirosh.notes.domain.Note
import nick.mirosh.notes.domain.repos.NoteRepository
import javax.inject.Inject

class NewNoteViewModel @Inject constructor(private val notesRepository: NoteRepository) :
    ViewModel() {

    private val _note = MutableLiveData<Note>()
    val note: LiveData<Note> = _note


    fun initNote(id: Int) {
        viewModelScope.launch {
            _note.value = notesRepository.getNoteById(id)
        }
    }

    fun createOrUpdateNote(text: String) {
        viewModelScope.launch {
            notesRepository.saveOrUpdate(note.value?.copy(content = text) ?: Note(content = text))
        }
    }
}