package nick.mirosh.notes.presentation.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import nick.mirosh.notes.data.di.MyApplication
import nick.mirosh.notes.data.di.viewmodels.ViewModelFactory
import nick.mirosh.notes.domain.Note
import nick.mirosh.notes.presentation.composables.NoteItem
import nick.mirosh.notes.presentation.new_note.NewNoteActivity
import nick.mirosh.notes.presentation.theme.MyApplicationTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)

        mainViewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        setContent {
            MyApplicationTheme {
                val notes =
                    remember { mainViewModel.notes.value ?: mutableListOf() }.toMutableStateList()
                MainScreen(notes = notes) {
                    startActivity(Intent(this@MainActivity, NewNoteActivity::class.java))
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        mainViewModel.getNotes()
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier, notes: List<Note>, onAddButton: () -> Unit) {

    Box(
        modifier = modifier.fillMaxSize(),
    ) {

        NotesList(notes)
        AddNoteButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {

            onAddButton()
        }
    }
}

@Composable
fun AddNoteButton(modifier: Modifier = Modifier, onCLicked: () -> Unit) {
    FloatingActionButton(
        onClick = onCLicked,
        contentColor = Color.White,
        modifier = modifier,
    ) {
        Icon(Icons.Filled.Add, contentDescription = "Add")
    }
}


@Preview(showBackground = true)
@Composable
fun NotesListPreview() {
    MyApplicationTheme {
        NotesList(listOf())
    }
}


@Composable
fun NotesList(list: List<Note>, modifier: Modifier = Modifier) {
    LazyColumn {
        items(list) {
            NoteItem(note = it, modifier = modifier.padding(8.dp))
        }
    }
}


@Composable
fun Greeting(
    name: String,
    modifier: Modifier = Modifier
) {

    Column {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting(name = "Android")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteInputDialog(onNoteEntered: (String) -> Unit, onDismiss: () -> Unit) {
    var noteText by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = { },
        title = { Text("Enter Your Note") },
        text = {
            TextField(
                value = noteText,
                onValueChange = { noteText = it },
                placeholder = { Text("Note") }
            )
        },
        confirmButton = {
            Button(
                onClick = {
                    onNoteEntered(noteText)
                }
            ) {
                Text("OK")
            }
        },
        dismissButton = {
            Button(
                onClick = onDismiss
            ) {
                Text("Cancel")
            }
        }
    )
}


@Preview
@Composable
fun NoteInputDialogPreview() {
    MyApplicationTheme {
        NoteInputDialog(onNoteEntered = {}, onDismiss = {})
    }
}


