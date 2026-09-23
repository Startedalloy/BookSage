package com.example.booksage.roomDB

import android.net.Uri
import kotlinx.coroutines.flow.Flow

class PdfRepository (private val dao: PdfDao){

    val pdfs : Flow<List<Pdf>> = dao.getPdfs()

    suspend fun addPdf(uri: Uri){

        dao.addUri(Pdf(uri = uri))
    }
}