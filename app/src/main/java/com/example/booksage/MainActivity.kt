package com.example.booksage

import android.content.Intent
import android.graphics.drawable.Icon
import android.net.Uri
import android.os.Bundle
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.booksage.HomeScreen.BookViewModel
import com.example.booksage.roomDB.BookApp
import com.example.booksage.roomDB.BookViewModelFactory
import com.example.booksage.ui.PdfFragmentHost
import com.example.booksage.ui.theme.BookSageTheme

class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BookSageTheme {
                val app = LocalContext.current.applicationContext as BookApp
                val bookViewModel: BookViewModel = viewModel(
                    factory = BookViewModelFactory(app.repository),)
                PdfScreen(bookViewModel)
            }
        }
    }
}


@Composable
fun PdfScreen(viewModel: BookViewModel) {
    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri: Uri? ->
        uri ?: return@rememberLauncherForActivityResult
        context.contentResolver.takePersistableUriPermission(
            uri, Intent.FLAG_GRANT_READ_URI_PERMISSION
        )
        viewModel.addPdf(uri)
    }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { launcher.launch(arrayOf("application/pdf")) }) {
                Text("+")
            }
        }) { padding ->
        if (viewModel.selectedUri == null) {
            PdfGrid(
                uris = viewModel.pdfUris, onPdfClick = { viewModel.selectPdf(it) })
        } else {
            Column(Modifier.padding(padding)) {
                TextButton(onClick = { viewModel.clearPdf() }) {
                    Text("← Back to grid")
                }
                PdfFragmentHost(uri = viewModel.selectedUri!!)
            }
        }
    }
}
