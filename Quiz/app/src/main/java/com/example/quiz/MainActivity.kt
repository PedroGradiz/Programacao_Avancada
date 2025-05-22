package com.example.quiz

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.quiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val questionTexts = arrayOf(
        "1. Qual é a principal vantagem do Kotlin em relação ao Java para desenvolvimento Android?",
        "2. Qual palavra-chave é usada para declarar uma variável imutável em Kotlin?",
        "3. Como se declara uma função em Kotlin?"
    )

    private val questionOptions = arrayOf(
        arrayOf("A) É mais lento que o Java", "B) Não é compatível com bibliotecas Java", "C) Sintaxe mais concisa e segura", "D) Requer hardware específico"),
        arrayOf("A) var", "B) const", "C) final", "D) val"),
        arrayOf("A) def minhaFuncao() {}", "B) fun minhaFuncao() {}", "C) function minhaFuncao() {}", "D) void minhaFuncao() {}")
    )

    private val correctAnswers = arrayOf(2, 3, 1) // base 0
    private var currentQuestionIndex = 0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        displayQuestion()

        binding.option1Button.setOnClickListener { checkAnswer(0) }
        binding.option2Button.setOnClickListener { checkAnswer(1) }
        binding.option3Button.setOnClickListener { checkAnswer(2) }
        binding.option4Button.setOnClickListener { checkAnswer(3) }
        binding.restartButton.setOnClickListener { restartQuiz() }
    }

    private fun correctButtonColors(buttonIndex: Int) {
        when (buttonIndex) {
            0 -> binding.option1Button.setBackgroundColor(Color.GREEN)
            1 -> binding.option2Button.setBackgroundColor(Color.GREEN)
            2 -> binding.option3Button.setBackgroundColor(Color.GREEN)
            3 -> binding.option4Button.setBackgroundColor(Color.GREEN)
        }
    }

    private fun wrongButtonColors(buttonIndex: Int) {
        when (buttonIndex) {
            0 -> binding.option1Button.setBackgroundColor(Color.RED)
            1 -> binding.option2Button.setBackgroundColor(Color.RED)
            2 -> binding.option3Button.setBackgroundColor(Color.RED)
            3 -> binding.option4Button.setBackgroundColor(Color.RED)
        }
    }

    private fun resetButtonColors() {
        val color = Color.rgb(50, 59, 96)
        binding.option1Button.setBackgroundColor(color)
        binding.option2Button.setBackgroundColor(color)
        binding.option3Button.setBackgroundColor(color)
        binding.option4Button.setBackgroundColor(color)
    }

    private fun showResults() {
        Toast.makeText(this, "Your score: $score out of ${questionTexts.size}", Toast.LENGTH_LONG).show()
        binding.restartButton.isEnabled = true
    }

    private fun displayQuestion() {
        binding.questionText.text = questionTexts[currentQuestionIndex]
        binding.option1Button.text = questionOptions[currentQuestionIndex][0]
        binding.option2Button.text = questionOptions[currentQuestionIndex][1]
        binding.option3Button.text = questionOptions[currentQuestionIndex][2]
        binding.option4Button.text = questionOptions[currentQuestionIndex][3]
        resetButtonColors()
    }

    private fun checkAnswer(selectedAnswerIndex: Int) {
        val correctAnswerIndex = correctAnswers[currentQuestionIndex]

        if (selectedAnswerIndex == correctAnswerIndex) {
            score++
            correctButtonColors(selectedAnswerIndex)
        } else {
            wrongButtonColors(selectedAnswerIndex)
            correctButtonColors(correctAnswerIndex)
        }

        if (currentQuestionIndex < questionTexts.size - 1) {
            currentQuestionIndex++
            binding.questionText.postDelayed({ displayQuestion() }, 1000)
        } else {
            showResults()
        }
    }

    private fun restartQuiz() {
        currentQuestionIndex = 0
        score = 0
        displayQuestion()
        binding.restartButton.isEnabled = false
    }
}