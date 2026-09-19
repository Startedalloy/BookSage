package com.example.booksage.HomeScreen


import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class BookViewModel : ViewModel() {

    var pdfUris by mutableStateOf<List<Uri>>(emptyList())
        private set
    var selectedUri by mutableStateOf<Uri?>(null)
        private set

    fun addPdf(uri: Uri) {
        if (uri !in pdfUris){
        pdfUris = pdfUris + uri
}
    }

    fun selectPdf(uri: Uri) {
        selectedUri = uri
    }

    fun clearPdf() {
        selectedUri = null
    }
}