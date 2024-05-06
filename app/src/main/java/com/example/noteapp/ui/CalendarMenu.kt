package com.example.noteapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.noteapp.data.NoteVariables

// Composable function to display the calendar menu
@Composable
fun CalendarMenu(
    onDismiss: () -> Unit
) {
    // List of weekdays
    val datesList = listOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")
    // Display weekdays row
    WeekdaysRow(datesList = datesList)
    // Display days grid
    DaysGrid()

    // Box to cover the entire screen and dismiss the calendar on click
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable(onClick = onDismiss)
    ) {}
}

// Composable function to display the row of weekdays
@Composable
fun WeekdaysRow(datesList: List<String>) {
    // Row to hold weekdays
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .padding(5.dp), // Added padding for better spacing
        horizontalArrangement = Arrangement.SpaceBetween // Space evenly between weekdays
    ) {
        // Loop through weekdays list and display each day
        datesList.forEach { day ->
            // Set text color based on the day of the week
            val textColor = when (day) {
                "Sat" -> Color.Blue // Saturday is displayed in blue
                "Sun" -> Color.Red // Sunday is displayed in red
                else -> Color.Black // Other days are displayed in black
            }

            // Display weekday box with the appropriate text color
            Box(
                modifier = Modifier
                    .weight(1.5f)
                    .aspectRatio(1.5f) // Set aspect ratio 1:1.5
                    .padding(5.dp)
                    .border(1.dp, Color.Black, shape = RoundedCornerShape(8.dp)) // Set border with 1dp width, black color, and rounded corners with 8dp radius
                    .clip(RoundedCornerShape(8.dp)), // Clip the box to have rounded corners
                contentAlignment = Alignment.Center
            ) {
                // Display weekday text with specified color
                Text(text = day.substring(0, 3), color = textColor)
            }
        }
    }
}

// Composable function to display the days grid of the calendar
@Composable
fun DaysGrid() {
    // Get the first day of the current month
    val currentMonthFirstDay = NoteVariables.selectedDate.withDayOfMonth(1)
    // Get the last day of the current month
    val lastDayOfMonth = currentMonthFirstDay.plusMonths(1).minusDays(1)

    // Determine the number of blank cells needed before the first day of the month
    val firstDayOfWeek = currentMonthFirstDay.dayOfWeek.value
    val numBlankCells = if (firstDayOfWeek == 7) 0 else firstDayOfWeek

    // Initialize calendar start date
    var calendarStartDate = currentMonthFirstDay.minusDays(numBlankCells.toLong())

    // Display log
    //Log.d("DatePicker", "=========================================================")
    //Log.d("DatePicker", "calendar Start Date : $calendarStartDate")
    //Log.d("DatePicker", "NoteVariables.selectedDate : ${NoteVariables.selectedDate}")
    //Log.d("DatePicker", "NoteVariables.currentDate : ${NoteVariables.currentDate}")
    //Log.d("DatePicker", "=========================================================")

    // Column to hold the days grid
    Column{
        // Loop through the weeks
        while (calendarStartDate <= lastDayOfMonth) {
            // Row to hold each week
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black)
                    .padding(vertical = 5.dp), // Add vertical padding to separate weeks
            ) {
                // Loop through the days of the week
                for (i in 1..7) {
                    // Check if the current day is in the current month
                    val isCurrentMonth = calendarStartDate.month == currentMonthFirstDay.month
                    // Store the current date
                    val date = calendarStartDate

                    // Determine box color based on current month and selected date
                    val boxColor = if (isCurrentMonth && calendarStartDate == NoteVariables.selectedDate) {
                        Color.Red // Set to red if it's the selected date
                    } else if (isCurrentMonth) {
                        Color.LightGray // Set to light gray for other days of the current month
                    } else {
                        Color.Green // Set to green for dates outside the current month
                    }

                    val textColor = when {
                        (isCurrentMonth && calendarStartDate == NoteVariables.selectedDate) -> Color.White // Set to white if it's the selected date
                        (isCurrentMonth && calendarStartDate.dayOfWeek.value == 6) -> Color.Blue // Set to blue if it's Saturday
                        (isCurrentMonth && calendarStartDate.dayOfWeek.value == 7) -> Color.Red // Set to red if it's Sunday
                        else -> Color.Black // Set to black for other days of the current month
                    }

                    // Box representing a day
                    Box(
                        modifier = Modifier
                            .weight(1.5f)
                            .aspectRatio(1.5f) // Set aspect ratio 1:1.5
                            .padding(5.dp)
                            .border(1.dp, Color.Black, shape = RoundedCornerShape(8.dp)) // Set border with 1dp width, black color, and rounded corners with 8dp radius
                            .clip(RoundedCornerShape(8.dp)) // Clip the box to have rounded corners
                            .background(if (isCurrentMonth) Color.LightGray else Color.Green)
                            .background(boxColor)
                            .clickable { NoteVariables.selectedDate = date }, // Handle click event
                        contentAlignment = Alignment.Center
                    ) {
                        // Display day of the month
                        Text(text = calendarStartDate.dayOfMonth.toString(), color = textColor)
                    }

                    // Increment current date for the next iteration
                    calendarStartDate = calendarStartDate.plusDays(1)
                }
            }
        }
        // Display selected date
        Text(text = "\n")
        Text(text = "Selected Date: ${NoteVariables.selectedDate}")
        Text(text = "Today's Date: ${NoteVariables.currentDate}")
    }
}
