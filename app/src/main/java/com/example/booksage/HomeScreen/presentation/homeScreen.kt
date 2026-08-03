package com.example.booksage.HomeScreen.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Preview(showSystemUi = true)
@Composable
fun homeScreen() {

    Column(
        modifier = Modifier.fillMaxSize(),
        Arrangement.Center,
        Alignment.CenterHorizontally
    ) {

        Text("Hello World")
    }
}