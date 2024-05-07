package com.example.noteapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
data class Memo(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val content: String,
    val basisDate: String
)