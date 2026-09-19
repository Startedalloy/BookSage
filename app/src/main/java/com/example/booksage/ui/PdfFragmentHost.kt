package com.example.booksage.ui

import android.annotation.SuppressLint
import android.net.Uri
import android.view.View
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentContainerView
import androidx.pdf.viewer.fragment.PdfViewerFragment

@SuppressLint("ContextCastToActivity")
@Composable
fun PdfFragmentHost(uri: Uri, modifier: Modifier = Modifier) {
    val activity = LocalContext.current as FragmentActivity
    val containerId = remember { View.generateViewId() }
    AndroidView(
        modifier = modifier.fillMaxSize(),
        factory = { context ->
            FragmentContainerView(context).apply {
                id = containerId
            }
        },
        update = { container ->
            // 1. Find existing fragment OR create a new one
            var fragment = activity.supportFragmentManager
                .findFragmentById(container.id) as? PdfViewerFragment
            if (fragment == null) {
                fragment = PdfViewerFragment()
                activity.supportFragmentManager
                    .beginTransaction()
                    .replace(container.id, fragment)
                    .commitNow()   // 2. Attach FIRST
            }
            // 3. Set URI ONLY after fragment is attached
            if (fragment.documentUri != uri) {
                fragment.documentUri = uri
            }
        }
    )
}


