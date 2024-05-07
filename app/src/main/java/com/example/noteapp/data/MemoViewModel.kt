package com.example.noteapp.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MemoViewModel(private val repository: MemoRepository) : ViewModel() {
    val memos = repository.memos.asLiveData()

    fun getByDate(basisDate: String) = viewModelScope.launch {
        repository.getByDate(basisDate)
    }
    fun insert(memo: Memo) = viewModelScope.launch {
        repository.insert(memo)
    }

    fun update(memo: Memo) = viewModelScope.launch {
        repository.update(memo)
    }

    fun delete(memo: Memo) = viewModelScope.launch {
        repository.delete(memo)
    }
}