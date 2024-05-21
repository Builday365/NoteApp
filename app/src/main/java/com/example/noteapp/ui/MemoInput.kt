
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemoInput(
    memoText: String,
    onTextChange: (String) -> Unit,
    onSaveClick: () -> Unit,
    onDismiss: () -> Unit
) {
    val textFieldColors = TextFieldDefaults.outlinedTextFieldColors(
        focusedBorderColor = Color(0xfff15c80), // 포커스된 상태의 외곽선 색상
        unfocusedBorderColor = Color(0xff777777) // 포커스가 해제된 상태의 외곽선 색상
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        OutlinedTextField(
            value = memoText,
            onValueChange = onTextChange,
            label = {
                Text(
                    text = "Enter your memo (${memoText.length})",
                    color = Color.Gray,
                    fontSize = 12.sp
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            colors = textFieldColors
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Button(
                onClick = onSaveClick,
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier.padding(end = 8.dp)
            ) {
                Text(
                    text = "Save",
                    fontSize = 12.sp,
                    modifier = Modifier.padding(horizontal = 0.dp, vertical = 0.dp)
                )
            }
            Button(
                onClick = onDismiss,
                shape = RoundedCornerShape(4.dp)
            ) {
                Text(
                    text = "Cancel",
                    fontSize = 12.sp,
                    modifier = Modifier.padding(horizontal = 0.dp, vertical = 0.dp)
                )
            }
        }
    }
}
