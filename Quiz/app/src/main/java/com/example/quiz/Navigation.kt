package com.example.quiz

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.NavType

@Composable
// Define a navegação entre ecrãs (start, quiz e fim) usando o NavController com argumentos opcionais.

fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "start") {
        composable("start") { TelaStart(navController) }
        composable("quiz") { QuizScreen(navController) }
        composable(
            route = "fim/{score}",
            arguments = listOf(navArgument("score") { type = NavType.IntType })
        ) { backStackEntry ->
            val score = backStackEntry.arguments?.getInt("score") ?: 0
            Telafim(navController, score)
        }
    }
}
