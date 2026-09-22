package com.example.booksage.HomeScreen


import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booksage.roomDB.Pdf
import com.example.booksage.roomDB.PdfDao
import kotlinx.coroutines.launch

class BookViewModel(private val dao: PdfDao) : ViewModel() {

    var pdfUris by mutableStateOf<List<Uri>>(emptyList())
        private set
    var selectedUri by mutableStateOf<Uri?>(null)
        private set

    init {
        viewModelScope.launch {
            dao.getPdfs().collect { pdfList ->

                pdfUris = pdfList.map { it.uri }
            }
        }
    }

    fun addPdf(uri: Uri) {
        viewModelScope.launch { dao.addUri(Pdf(uri = uri)) }
    }

    fun selectPdf(uri: Uri) {
        selectedUri = uri
    }

    fun clearPdf() {
        selectedUri = null
    }
}