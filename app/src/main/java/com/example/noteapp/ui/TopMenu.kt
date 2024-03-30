package com.example.noteapp.ui

import DatePicker
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.noteapp.R
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale


@RequiresApi(Build.VERSION_CODES.O)
@Composable

fun TopMenu(){
    var isCalendarVisible by remember { mutableStateOf(false) }
    var currentDate by remember { mutableStateOf(LocalDate.now()) } // 현재 날짜를 기억하는 변수

    val iconResId = when (currentDate.dayOfWeek) {  // Check the current day of the week and select a vector image accordingly.
        DayOfWeek.MONDAY -> R.drawable.calendar_mon
        DayOfWeek.TUESDAY -> R.drawable.calendar_tue
        DayOfWeek.WEDNESDAY -> R.drawable.calendar_wed
        DayOfWeek.THURSDAY -> R.drawable.calendar_thu
        DayOfWeek.FRIDAY -> R.drawable.calendar_fri
        DayOfWeek.SATURDAY -> R.drawable.calendar_sat
        DayOfWeek.SUNDAY -> R.drawable.calendar_sun
        else -> error("Invalid day of the week")
    }
    Column {
        Row(modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black)
            .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top,
        )

        {
            Icon(painterResource(id = R.drawable.main_menu_icon),   // From SVG file to XML format and added it to the res folder.
                modifier = Modifier
                    .size(32.dp)
                    .padding(0.dp), // Icon size set
                tint = Color.Unspecified,   // SVG Original color
                contentDescription = "Main menu icon")
            Icon(painterResource(id = R.drawable.main_left_double_arrow_icon),   // From SVG file to XML format and added it to the res folder.
                modifier = Modifier
                    .size(32.dp)
                    .padding(0.dp) // Icon size set
                    .clickable { currentDate = currentDate.minusMonths(1) }, // Change to previous month
            tint = Color.Unspecified,   // SVG Original color
                contentDescription = "Main left double arrow icon")
            Icon(
                painterResource(id = R.drawable.main_left_single_arrow_icon),
                modifier = Modifier
                    .size(32.dp)
                    .padding(0.dp) // Icon size set
                    .clickable { currentDate = currentDate.minusDays(1) }, // Change to yesterday
                tint = Color.Unspecified, // SVG Original color
                contentDescription = "Main left single arrow icon"
            )
            Text(currentDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd (E)", Locale.ENGLISH)).toString(),
                fontFamily = FontFamily.SansSerif,
                fontSize = 16.sp,
                color = Color(0xFF777777),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(4.dp)
                                    .width(130.dp)
                                    //.height(32.dp)
                                    .clickable { currentDate = LocalDate.now()}, // Change to today
            )

            Icon(painterResource(id = R.drawable.main_right_single_arrow_icon),   // From SVG file to XML format and added it to the res folder.
                modifier = Modifier
                    .size(32.dp)
                    .padding(0.dp) // Icon size set
                    .clickable { currentDate = currentDate.plusDays(1) }, // Change to tomorrow
                tint = Color.Unspecified,   // SVG Original color
                contentDescription = "Main right single arrow icon")
            Icon(painterResource(id = R.drawable.main_right_double_arrow_icon),   // From SVG file to XML format and added it to the res folder.
                modifier = Modifier
                    .size(32.dp)
                    .padding(0.dp) // Icon size set
                    .clickable { currentDate = currentDate.plusMonths(1) }, // Change to next month
                tint = Color.Unspecified,   // SVG Original color
                contentDescription = "Main right double arrow icon")


            Icon(painter = painterResource(id = iconResId),
                modifier = Modifier
                    .size(32.dp)    // Icon size set
                    .padding(0.dp)
                    .clickable { isCalendarVisible = !isCalendarVisible },
                tint = Color.Unspecified,   // SVG Original color
                contentDescription = "Calendar Icon")
        }
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(color = Color(0XFF777777))
        )
        if (isCalendarVisible) {
            DatePicker()
        }
    }
}


