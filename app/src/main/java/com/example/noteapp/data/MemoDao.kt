package com.example.noteapp.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MemoDao {
    @Query("SELECT * FROM memo WHERE basisDate = :basisDate")
    fun getByDate(basisDate: String): Flow<List<Memo>>

    @Insert
    suspend fun insert(memo: Memo)

    @Update
    suspend fun update(memo: Memo)

    @Delete
    suspend fun delete(memo: Memo)
}
