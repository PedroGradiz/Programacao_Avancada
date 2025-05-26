package com.example.quiz

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.quiz.databinding.ActivityTelafimBinding  // <-- note aqui

class TelaFim : AppCompatActivity() {

    private lateinit var binding: ActivityTelafimBinding   // <-- e aqui

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTelafimBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val score = intent.getIntExtra("score", 0)
        val total = intent.getIntExtra("total", 0)

        binding.scoreTextView.text = "Você acertou $score de $total perguntas!"

        binding.startButton.setOnClickListener {
            val intent = Intent(this, TelaStart::class.java)
            startActivity(intent)
            finish()
        }
    }
}
