package com.example.quiz

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.quiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    data class Pergunta(
        val texto: String,
        val opcoesEmbaralhadas: List<String>,
        val indiceCorreto: Int
    )

    private lateinit var perguntas: List<Pergunta>
    private var currentQuestionIndex = 0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Banco de 10 perguntas
        val perguntasOriginais = listOf(
            Triple("Qual é a principal vantagem do Kotlin em relação ao Java para desenvolvimento Android?",
                listOf("É mais lento que o Java", "Não é compatível com bibliotecas Java", "Sintaxe mais concisa e segura", "Requer hardware específico"), 2),

            Triple("Qual palavra-chave é usada para declarar uma variável imutável em Kotlin?",
                listOf("var", "const", "final", "val"), 3),

            Triple("Como se declara uma função em Kotlin?",
                listOf("def minhaFuncao() {}", "fun minhaFuncao() {}", "function minhaFuncao() {}", "void minhaFuncao() {}"), 1),

            Triple("Qual estrutura de controle substitui o switch do Java em Kotlin?",
                listOf("switch", "when", "choose", "select"), 1),

            Triple("Qual é o tipo padrão para números inteiros em Kotlin?",
                listOf("Int", "Long", "Byte", "Float"), 0),

            Triple("Qual dessas não é uma função de escopo em Kotlin?",
                listOf("let", "apply", "also", "fetch"), 3),

            Triple("Como declarar uma lista mutável em Kotlin?",
                listOf("listOf()", "arrayListOf()", "mutableListOf()", "setOf()"), 2),

            Triple("O que o operador '!!' faz em Kotlin?",
                listOf("Declara uma variável", "Garante não null, ou lança exceção", "Converte para Boolean", "Define como nulo"), 1),

            Triple("Qual função converte string para inteiro em Kotlin?",
                listOf("toInt()", "parseInt()", "toInteger()", "intValue()"), 0),

            Triple("O que significa 'val nome: String?' em Kotlin?",
                listOf("nome nunca pode ser nulo", "nome pode ser nulo", "nome é mutável", "nome é constante"), 1)
        )

        // Selecionar 5 perguntas aleatórias e embaralhar suas opções
        perguntas = perguntasOriginais.shuffled().take(5).map { (texto, opcoes, indiceCorretoOriginal) ->
            val opcoesComIndice = opcoes.mapIndexed { i, opcao -> Pair(i, opcao) }
            val opcoesEmbaralhadasComIndices = opcoesComIndice.shuffled()
            val novasOpcoes = opcoesEmbaralhadasComIndices.map { it.second }
            val novoIndiceCorreto = opcoesEmbaralhadasComIndices.indexOfFirst { it.first == indiceCorretoOriginal }

            Pergunta(texto, novasOpcoes, novoIndiceCorreto)
        }

        displayQuestion()

        binding.option1Button.setOnClickListener { checkAnswer(0) }
        binding.option2Button.setOnClickListener { checkAnswer(1) }
        binding.option3Button.setOnClickListener { checkAnswer(2) }
        binding.option4Button.setOnClickListener { checkAnswer(3) }

        binding.nextButton.setOnClickListener {
            if (currentQuestionIndex < perguntas.size - 1) {
                currentQuestionIndex++
                displayQuestion()
                setButtonsEnabled(true)
            } else {
                goToTelaFim()
            }
        }
    }

    private fun displayQuestion() {
        val perguntaAtual = perguntas[currentQuestionIndex]
        binding.questionText.text = perguntaAtual.texto
        binding.option1Button.text = perguntaAtual.opcoesEmbaralhadas[0]
        binding.option2Button.text = perguntaAtual.opcoesEmbaralhadas[1]
        binding.option3Button.text = perguntaAtual.opcoesEmbaralhadas[2]
        binding.option4Button.text = perguntaAtual.opcoesEmbaralhadas[3]

        resetButtonColors()
        binding.nextButton.visibility = View.GONE
    }

    private fun checkAnswer(selectedAnswerIndex: Int) {
        val correctAnswerIndex = perguntas[currentQuestionIndex].indiceCorreto

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
        intent.putExtra("total", perguntas.size)
        startActivity(intent)
        finish()
    }
}
