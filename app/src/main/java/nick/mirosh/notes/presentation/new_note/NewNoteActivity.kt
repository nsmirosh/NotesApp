package nick.mirosh.notes.presentation.new_note

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dagger.hilt.android.AndroidEntryPoint
import nick.mirosh.notes.data.di.MyApplication
import nick.mirosh.notes.domain.Note
import nick.mirosh.notes.presentation.main.MainViewModel
import nick.mirosh.notes.presentation.theme.MyApplicationTheme
import javax.inject.Inject

@AndroidEntryPoint
class NewNoteActivity : ComponentActivity() {

    private val newNoteViewModel: NewNoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val extras = intent.extras
        val noteId = extras?.getInt("noteId") ?: -1

        if (noteId != -1) {
            newNoteViewModel.initNote(noteId)
        }
        setContent {
            MyApplicationTheme {
                NewNoteScreen(newNoteViewModel) {
                    newNoteViewModel.createOrUpdateNote(it)
                    finish()
                }
            }
        }
    }
}

@Composable
fun NewNoteScreen(
    newNoteViewModel: NewNoteViewModel,
    onCompleteButtonClicked: (String) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {

        val note by newNoteViewModel.note.observeAsState(Note(content = ""))

        var textFieldValue by remember { mutableStateOf(TextFieldValue(note.content)) }

        Column {
            TextEntryField(label = "Enter your note", text = textFieldValue) {
                textFieldValue = it
            }
            CompleteButton {
                onCompleteButtonClicked(textFieldValue.text)
            }
        }
    }
}

//A composable TextField that allows to enter text
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextEntryField(
    label: String,
    text: TextFieldValue,
    onValueChanged: (TextFieldValue) -> Unit
) {

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = label)
        TextField(
            value = text,
            onValueChange = onValueChanged,
            modifier = Modifier.fillMaxWidth()
        )
    }
}


//An addNote button that is the width of the screen with a 16dp padding on the sides and rectangular corners
@Composable
fun CompleteButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(text = "Add Note")
    }
}

@Preview
@Composable
fun CompleteButtonPreview() {
    MyApplicationTheme {
        CompleteButton {

        }
    }
}

@Preview
@Composable
fun TextEntryFieldPreview() {
    MyApplicationTheme {
        TextEntryField(label = "Title", text = TextFieldValue("")) { /*TODO*/ }
    }
}




