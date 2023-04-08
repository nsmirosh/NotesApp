package nick.mirosh.notes.presentation.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import nick.mirosh.notes.domain.Note
import nick.mirosh.notes.domain.repos.NoteRepository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val newNoteRepository: NoteRepository): ViewModel() {

    private val _notes = MutableLiveData<List<Note>>()
    val notes: LiveData<List<Note>> = _notes

    fun getNotes() {

        Log.d("MainViewModel", "getNotes running")
        viewModelScope.launch {
            val notes = newNoteRepository.getAllNotes()
            Log.d("MainViewModel", "getAllNotes() = $notes")
            _notes.value = notes
        }
    }
}