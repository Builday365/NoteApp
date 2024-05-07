package com.example.noteapp.data

import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

class MemoRepository(private val memoDao: MemoDao) {
    val memos: Flow<List<Memo>> = memoDao.getByDate(LocalDate.now().toString())

    fun getByDate(basisDate: String){
        memoDao.getByDate(basisDate)
    }
    suspend fun insert(memo: Memo) {
        memoDao.insert(memo)
    }

    suspend fun update(memo: Memo) {
        memoDao.update(memo)
    }

    suspend fun delete(memo: Memo) {
        memoDao.delete(memo)
    }
}