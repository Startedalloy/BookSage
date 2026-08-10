package com.example.booksage

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentContainerView
import androidx.pdf.viewer.fragment.PdfViewerFragment
import com.example.booksage.ui.theme.BookSageTheme

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BookSageTheme {

                PdfScreen()
            }
        }
    }
}


@Composable
fun PdfScreen() {

    var result by remember { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri: Uri? ->
        result = uri
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        Arrangement.Center,
        Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { launcher.launch(arrayOf("application/pdf")) },
            colors = ButtonDefaults.buttonColors(Color.Blue)
        ) {
            Text("Pick Pdf", color = Color.White)
        }
        result?.let { uri -> PdfFragmentHost(uri) }

    }

}

@SuppressLint("ContextCastToActivity")
@Composable
fun PdfFragmentHost(uri: Uri) {

    val activity = LocalContext.current as FragmentActivity

    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { context ->
            FragmentContainerView(context).apply {
                id = View.generateViewId()
            }
        },
        update = { container ->
            val fragment = PdfViewerFragment().apply {
                documentUri = uri
            }
            activity.supportFragmentManager
                .beginTransaction()
                .replace(container.id, fragment)
                .commitNow()
        }
    )

}
