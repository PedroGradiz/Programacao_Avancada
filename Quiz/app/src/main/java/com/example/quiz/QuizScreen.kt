package com.example.quiz

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.runtime.LaunchedEffect

data class Pergunta(val pergunta: String, val opcoes: List<String>, val respostaCerta: Int)
// Composable que apresenta 5 perguntas aleatórias e calcula a pontuação, navegando no fim para o ecrã final.

@Composable
fun QuizScreen(navController: NavController) {

    val todasPerguntas = listOf(
        Pergunta(
            "Qual é a principal vantagem do Kotlin em relação ao Java para desenvolvimento Android?",
            listOf("É mais lento que o Java", "Não é compatível com bibliotecas Java", "Sintaxe mais concisa e segura", "Requer hardware específico"),
            2
        ),
        Pergunta(
            "Qual palavra-chave é usada para declarar uma variável imutável em Kotlin?",
            listOf("var", "const", "final", "val"),
            3
        ),
        Pergunta(
            "Como se declara uma função em Kotlin?",
            listOf("def minhaFuncao() {}", "fun minhaFuncao() {}", "function minhaFuncao() {}", "void minhaFuncao() {}"),
            1
        ),
        Pergunta(
            "Qual estrutura de controle substitui o switch do Java em Kotlin?",
            listOf("switch", "when", "choose", "select"),
            1
        ),
        Pergunta(
            "Qual é o tipo padrão para números inteiros em Kotlin?",
            listOf("Int", "Long", "Byte", "Float"),
            0
        ),
        Pergunta(
            "Qual dessas não é uma função de escopo em Kotlin?",
            listOf("let", "apply", "also", "fetch"),
            3
        ),
        Pergunta(
            "Como declarar uma lista mutável em Kotlin?",
            listOf("listOf()", "arrayListOf()", "mutableListOf()", "setOf()"),
            2
        ),
        Pergunta(
            "O que o operador '!!' faz em Kotlin?",
            listOf("Declara uma variável", "Garante não null, ou lança exceção", "Converte para Boolean", "Define como nulo"),
            1
        ),
        Pergunta(
            "Qual função converte string para inteiro em Kotlin?",
            listOf("toInt()", "parseInt()", "toInteger()", "intValue()"),
            0
        ),
        Pergunta(
            "O que significa 'val nome: String?' em Kotlin?",
            listOf("nome nunca pode ser nulo", "nome pode ser nulo", "nome é mutável", "nome é constante"),
            1
        )
    )

    // Selecionar 5 perguntas aleatórias
    val perguntasAleatorias = remember {
        todasPerguntas.shuffled().take(5).map { pergunta ->
            val opcoesEmbaralhadas = pergunta.opcoes.shuffled()
            val novaRespostaCerta = opcoesEmbaralhadas.indexOf(pergunta.opcoes[pergunta.respostaCerta])
            Pergunta(pergunta.pergunta, opcoesEmbaralhadas, novaRespostaCerta)
        }
    }

    var indice by remember { mutableStateOf(0) }
    var pontuacao by remember { mutableStateOf(0) }
    var respostaSelecionada by remember { mutableStateOf<Int?>(null) }
    var mostrarResposta by remember { mutableStateOf(false) }

    // Quando acaba o quiz, navega para a tela fim com a pontuação
    if (indice >= perguntasAleatorias.size) {
        LaunchedEffect(Unit) {
            navController.navigate("fim/$pontuacao")
        }
        return
    }

    val perguntaAtual = perguntasAleatorias[indice]

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Pergunta ${indice + 1} / ${perguntasAleatorias.size}", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(16.dp))
            Text(perguntaAtual.pergunta, style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(24.dp))

            perguntaAtual.opcoes.forEachIndexed { index, opcao ->
                Button(
                    onClick = {
                        if (!mostrarResposta) {
                            respostaSelecionada = index
                            mostrarResposta = true
                            if (index == perguntaAtual.respostaCerta) {
                                pontuacao++
                            }
                        }
                    },
                    enabled = !mostrarResposta,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Text(opcao)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (mostrarResposta) {
                val mensagem = if (respostaSelecionada == perguntaAtual.respostaCerta) {
                    "Correto!"
                } else {
                    "Errado! A resposta correta é: ${perguntaAtual.opcoes[perguntaAtual.respostaCerta]}"
                }

                Text(
                    mensagem,
                    style = MaterialTheme.typography.bodyLarge,
                    color = if (respostaSelecionada == perguntaAtual.respostaCerta)
                        MaterialTheme.colorScheme.primary
                    else
                        MaterialTheme.colorScheme.error
                )
                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = {
                    indice++
                    respostaSelecionada = null
                    mostrarResposta = false
                }) {
                    Text("Seguinte")
                }
            }
        }
    }
}
