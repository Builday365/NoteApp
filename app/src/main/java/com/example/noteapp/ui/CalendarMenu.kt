import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters
import java.util.Locale

@Composable
fun DatePicker() {
    val datesList = listOf<String>("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")
    var dayCounter: Int = 1
    val week: Int = 1
    Column(Modifier.fillMaxWidth()) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(5.dp)
                .background(Color.LightGray),
            horizontalArrangement = Arrangement.Center
        ) {
            datesList.forEach {
                Box(
                    Modifier
                        .weight(1f)
                        .padding(5.dp)
                        .background(Color.Gray),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = it.substring(0, 3))
                }
            }
        }
        var initWeekday = 7 // wednesday
        while (dayCounter <= 31) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
                    .background(Color.Yellow),
            ) {
                if (initWeekday > 0) {
                    repeat(initWeekday) {
                        Spacer(modifier = Modifier.weight(1f).fillMaxWidth().background(Color.Black))
                    }
                }
                for (i in week..(7 - initWeekday)) {
                    if (dayCounter <= 31) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .weight(1f)
                                .background(Color.LightGray)
                                .padding(10.dp)
                        ) {
                            Text(text = dayCounter++.toString())
                        }
                    } else {
                        Spacer(modifier = Modifier.weight(1f).fillMaxWidth().background(Color.Red))
                    }
                }
                initWeekday = 0
            }
        }
    }
}
