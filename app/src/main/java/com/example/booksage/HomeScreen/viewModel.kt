package com.example.booksage.HomeScreen


import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booksage.roomDB.PdfRepository
import kotlinx.coroutines.launch

class BookViewModel(private val repository: PdfRepository) : ViewModel() {

    var pdfUris by mutableStateOf<List<Uri>>(emptyList())
        private set
    var selectedUri by mutableStateOf<Uri?>(null)
        private set

    init {
        viewModelScope.launch {
            repository.pdfs.collect {pdfList->
                pdfUris = pdfList.map { it.uri }
            }
        }
    }

    fun addPdf(uri: Uri) {
        viewModelScope.launch { repository.addPdf(uri) }
    }

    fun selectPdf(uri: Uri) {
        selectedUri = uri
    }

    fun clearPdf() {
        selectedUri = null
    }
}