package com.example.booksage.roomDB

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.booksage.HomeScreen.BookViewModel

class BookViewModelFactory(private val dao: PdfDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return BookViewModel(dao) as T
    }
}