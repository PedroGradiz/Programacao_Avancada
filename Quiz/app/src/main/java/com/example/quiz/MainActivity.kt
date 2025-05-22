package com.example.quiz

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private val questions=arrayOf("1. Qual é a principal vantagem do Kotlin em relação ao Java para desenvolvimento Android?",
        "2.Qual palavra-chave é usada para declarar uma variável imutável em Kotlin?",
        "4.Como se declara uma função em Kotlin?")

    private val questions = arrayOf(
        arrayOf("A) É mais lento que o Java", "B) Não é compatível com bibliotecas Java", "C) Sintaxe mais concisa e segura", "D) Requer hardware específico"),
        arrayOf("A) var", "B) const", "C) final", "D) val"),
        arrayOf("A) def minhaFuncao() {}", "B) fun minhaFuncao() {}", "C) function minhaFuncao() {}", "D) void minhaFuncao() {}")
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}