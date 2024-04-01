package com.example.noteapp.ui
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.noteapp.data.NoteVariables

@Composable
fun CalendarMenu() {
    val datesList = listOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")
    WeekdaysRow(datesList = datesList)
    DaysGrid()
}

@Composable
fun WeekdaysRow(datesList: List<String>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .background(Color.LightGray),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        datesList.forEach { day ->
            WeekdayBox(day = day)
        }
    }
}

@Composable
fun WeekdayBox(day: String) {
    Box(
        modifier = Modifier
            .width(40.dp) // 고정 너비
            //.weight(1f) // 화면 너비를 기준으로 가중치를 할당
            .height(40.dp) // 고정 높이
            .padding(5.dp)
            .background(Color.Blue),
        contentAlignment = Alignment.Center
    ) {
        Text(text = day.substring(0, 3))
    }
}

@Composable
fun DaysGrid() {
    val currentMonthFirstDay = NoteVariables.selectedDate.withDayOfMonth(1)
    val lastDayOfMonth = currentMonthFirstDay.plusMonths(1).minusDays(1)

    // Determine the number of blank cells needed before the first day of the month
    val firstDayOfWeek = currentMonthFirstDay.dayOfWeek.value
    val numBlankCells = if (firstDayOfWeek == 7) 0 else firstDayOfWeek

    // Initialize currentDay to the first day of the month
    var calendarStartDate = currentMonthFirstDay.minusDays(numBlankCells.toLong())

    Log.d("DatePicker", "=========================================================")
    Log.d("DatePicker", "calendar Start Date : $calendarStartDate")
    Log.d("DatePicker", "NoteVariables.selectedDate : ${NoteVariables.selectedDate}")
    Log.d("DatePicker", "NoteVariables.currentDate : ${NoteVariables.currentDate}")
    Log.d("DatePicker", "=========================================================")

    Column {
        // Loop through the weeks
        while (calendarStartDate <= lastDayOfMonth) {
            Row(Modifier.fillMaxWidth()) {
                // Loop through the days of the week
                for (i in 1..7) {
                    val isCurrentMonth = calendarStartDate.month == currentMonthFirstDay.month
                    val date = calendarStartDate // 날짜 저장

                    val boxColor = if (isCurrentMonth && calendarStartDate == NoteVariables.selectedDate) {
                        Color.Red // Set to red if it's the selected date
                    } else if (isCurrentMonth) {
                        Color.LightGray // Set to light gray for other days of the current month
                    } else {
                        Color.Green // Set to green for dates outside the current month
                    }

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(5.dp)
                            .background(boxColor)
                            .padding(10.dp)
                            .clickable { NoteVariables.selectedDate = date }, // 클릭 이벤트 처리
                        contentAlignment = Alignment.Center
                    ) {
                        if (isCurrentMonth) {
                            Text(text = calendarStartDate.dayOfMonth.toString())
                        } else {
                            Text(text = calendarStartDate.dayOfMonth.toString(), color = Color.White)
                        }
                    }

                    // Increment currentDay for the next iteration
                    calendarStartDate = calendarStartDate.plusDays(1)
                }
            }
        }
        Text(text = "선택된 날짜 :" + NoteVariables.selectedDate.toString())
        Text(text = "\n")
        Text(text = "다음달의 시작 날짜 :$calendarStartDate")
        Text(text = "오늘 날짜 :" + NoteVariables.currentDate.toString())
    }
}

