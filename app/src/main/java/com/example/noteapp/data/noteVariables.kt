package com.example.noteapp.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.noteapp.R
import java.time.DayOfWeek
import java.time.LocalDate

class NoteVariables {
    companion object {
        var currentDate: LocalDate by mutableStateOf(LocalDate.now())
        var selectedDate: LocalDate by mutableStateOf(LocalDate.now())
        var isCalendarVisible by mutableStateOf(false) // isCalendarVisible를 클래스 내부에 직접 선언
        var isMemoVisible: Boolean by mutableStateOf(false) // addNewMemo 변수 선언
        val iconResId: Int
            get() {
                return when (selectedDate.dayOfWeek) { // selectedDate로 수정
                    DayOfWeek.MONDAY -> R.drawable.calendar_mon
                    DayOfWeek.TUESDAY -> R.drawable.calendar_tue
                    DayOfWeek.WEDNESDAY -> R.drawable.calendar_wed
                    DayOfWeek.THURSDAY -> R.drawable.calendar_thu
                    DayOfWeek.FRIDAY -> R.drawable.calendar_fri
                    DayOfWeek.SATURDAY -> R.drawable.calendar_sat
                    DayOfWeek.SUNDAY -> R.drawable.calendar_sun
                    else -> error("Invalid day of the week")
                }
            }
    }
}
