package com.example.quiz

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
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

    private val correctAnswers = arrayOf(2, 3, 1) // índice base 0
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

        binding.nextButton.setOnClickListener {
            if (currentQuestionIndex < questionTexts.size - 1) {
                currentQuestionIndex++
                displayQuestion()
                setButtonsEnabled(true)
            } else {
                goToTelaFim()
            }
        }
    }

    private fun displayQuestion() {
        binding.questionText.text = questionTexts[currentQuestionIndex]
        binding.option1Button.text = questionOptions[currentQuestionIndex][0]
        binding.option2Button.text = questionOptions[currentQuestionIndex][1]
        binding.option3Button.text = questionOptions[currentQuestionIndex][2]
        binding.option4Button.text = questionOptions[currentQuestionIndex][3]

        resetButtonColors()
        binding.nextButton.visibility = View.GONE
    }

    private fun checkAnswer(selectedAnswerIndex: Int) {
        val correctAnswerIndex = correctAnswers[currentQuestionIndex]

        setButtonsEnabled(false)

        if (selectedAnswerIndex == correctAnswerIndex) {
            score++
            setButtonColor(selectedAnswerIndex, Color.GREEN)
        } else {
            setButtonColor(selectedAnswerIndex, Color.RED)
            setButtonColor(correctAnswerIndex, Color.GREEN)
        }

        binding.nextButton.visibility = View.VISIBLE
    }

    private fun setButtonColor(index: Int, color: Int) {
        when (index) {
            0 -> binding.option1Button.setBackgroundColor(color)
            1 -> binding.option2Button.setBackgroundColor(color)
            2 -> binding.option3Button.setBackgroundColor(color)
            3 -> binding.option4Button.setBackgroundColor(color)
        }
    }

    private fun resetButtonColors() {
        val defaultColor = Color.rgb(50, 59, 96)
        binding.option1Button.setBackgroundColor(defaultColor)
        binding.option2Button.setBackgroundColor(defaultColor)
        binding.option3Button.setBackgroundColor(defaultColor)
        binding.option4Button.setBackgroundColor(defaultColor)
    }

    private fun setButtonsEnabled(enabled: Boolean) {
        binding.option1Button.isEnabled = enabled
        binding.option2Button.isEnabled = enabled
        binding.option3Button.isEnabled = enabled
        binding.option4Button.isEnabled = enabled
    }

    private fun goToTelaFim() {
        val intent = Intent(this, TelaFim::class.java)
        intent.putExtra("score", score)
        intent.putExtra("total", questionTexts.size)
        startActivity(intent)
        finish()
    }
}
