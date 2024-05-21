
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.noteapp.R
import com.example.noteapp.data.NoteVariables

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMemo() {
    val (memoText, setMemoText) = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.Transparent)
            .padding(8.dp),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Bottom
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent)
                .padding(8.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.Bottom
        ) {
            Icon(
                painter = painterResource(id = R.drawable.memo_box_left),
                tint = Color.Unspecified, // Retain the original SVG color
                modifier = Modifier
                    .size(60.dp)
                    .clickable { NoteVariables.isMemoVisible = !NoteVariables.isMemoVisible } // Toggle memo input visibility on click
                    .padding(8.dp),
                contentDescription = "Add Memo icon"
            )
        }
    }
}

// Preview function
@Preview
@Composable
fun AddMemoPreview() {
    AddMemo()
}
