package nick.mirosh.notes.presentation.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import nick.mirosh.notes.domain.Note

//create a composable

@Composable
fun NoteItem(note: Note, modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
            .padding(
                2.dp
            )
    ) {

        Row(modifier = modifier.align(Alignment.CenterHorizontally)) {
            Text(modifier = modifier.padding(start = 8.dp), text = note.content)
        }
    }
}