package com.example.tiaapp.screens

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.speech.tts.TextToSpeech
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.background
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.*
import androidx.compose.ui.semantics.*
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import java.util.*

@Composable
fun MainScreen() {
    val context = LocalContext.current
    val haptic = LocalHapticFeedback.current

    var tts: TextToSpeech? by remember {
        mutableStateOf(null)
    }

    LaunchedEffect(Unit) {
        tts = TextToSpeech(context) {
            if (it == TextToSpeech.SUCCESS) {
                tts?.language = Locale.US
                tts?.speak("Welcome to TIA Navigation", TextToSpeech.QUEUE_FLUSH, null, null)
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            tts?.shutdown()
        }
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Indoor Navigation",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.semantics {
                    contentDescription = "Indoor Navigation heading"
                }
            )

            Button(
                onClick = {
                    haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                    tts?.speak("Starting navigation", TextToSpeech.QUEUE_FLUSH, null, null)
                    Toast.makeText(context, "Navigation started", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier
                    .semantics {
                        contentDescription = "Start navigation button"
                    }
            ) {
                Text("Start Navigation")
            }
        }
    }
}

private fun checkCameraPermission(context: Context): Boolean {
    return ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
}
