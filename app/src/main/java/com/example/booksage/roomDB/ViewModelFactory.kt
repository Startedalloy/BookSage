package com.example.booksage.roomDB

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.booksage.HomeScreen.BookViewModel

class BookViewModelFactory(private val repository: PdfRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return BookViewModel(repository) as T
    }
}