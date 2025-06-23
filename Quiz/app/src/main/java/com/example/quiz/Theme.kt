package com.example.quiz

import androidx.compose.material3.*
import androidx.compose.runtime.Composable

// Define o tema claro da aplicação, aplicando esquema de cores e tipografia.

@Composable
fun QuizTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = lightColorScheme(),
        typography = Typography(),
        content = content
    )
}
