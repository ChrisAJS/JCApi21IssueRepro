package com.repro.jclibrary

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ExposedComposable() {
    Row {
        Text("This comes from")
        Spacer(modifier = Modifier.weight(1f))
        Text("The library")
    }
}