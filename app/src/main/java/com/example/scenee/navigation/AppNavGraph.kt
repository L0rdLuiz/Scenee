package com.example.scenee.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.scenee.ui.detalhes.DetalhesScreen
import com.example.scenee.ui.filmes.FilmesScreen
import com.example.scenee.ui.login.LoginScreen
import com.example.scenee.ui.login.SignUpScreen
import com.example.scenee.ui.perfil.PerfilScreen
import com.example.scenee.viewmodel.LoginViewModel
import com.example.scenee.viewmodel.NavigationEvent

object Graph {
    const val AUTHENTICATION = "auth_graph"
    const val HOME = "home_graph"
}

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()
    val loginViewModel: LoginViewModel = viewModel()

    // Observador de eventos de navegação do ViewModel
    LaunchedEffect(Unit) {
        loginViewModel.navigationEvent.collect { event ->
            when (event) {
                is NavigationEvent.NavigateToHome -> {
                    navController.navigate(Graph.HOME) {
                        // Limpa o backstack para não voltar para a tela de login
                        popUpTo(Graph.AUTHENTICATION) { inclusive = true }
                    }
                }
                is NavigationEvent.NavigateToSignUp -> navController.navigate("signup")
                is NavigationEvent.NavigateToLogin -> navController.navigate("login")
            }
        }
    }

    NavHost(navController = navController, startDestination = Graph.AUTHENTICATION) {
        // Gráfico de Autenticação (Login, Cadastro)
        authGraph(navController, loginViewModel)

        // Gráfico Principal (Após o login)
        homeGraph(navController)
    }
}

fun NavGraphBuilder.authGraph(navController: NavHostController, loginViewModel: LoginViewModel) {
    navigation(startDestination = "login", route = Graph.AUTHENTICATION) {
        composable("login") {
            val uiState by loginViewModel.uiState.collectAsState()
            LoginScreen(
                uiState = uiState,
                onEmailChange = loginViewModel::onEmailChange,
                onPasswordChange = loginViewModel::onPasswordChange,
                onLoginClick = loginViewModel::login,
                onNavigateToSignUp = loginViewModel::onNavigateToSignUpClicked
            )
        }
        composable("signup") {
            val uiState by loginViewModel.uiState.collectAsState()
            SignUpScreen(
                uiState = uiState,
                onNameChange = loginViewModel::onNameChange,
                onEmailChange = loginViewModel::onEmailChange,
                onPasswordChange = loginViewModel::onPasswordChange,
                onSignUpClick = loginViewModel::signUp,
                onNavigateToLogin = loginViewModel::onNavigateToLoginClicked
            )
        }
    }
}

fun NavGraphBuilder.homeGraph(navController: NavHostController) {
    navigation(startDestination = "filmes", route = Graph.HOME) {
        composable("filmes") {
            FilmesScreen(onNavigate = { dest -> navController.navigate(dest) })
        }
        composable("perfil") {
            PerfilScreen(onNavigate = { dest -> navController.navigate(dest) })
        }
        composable(
            route = "detalhes/{movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.IntType })
        ) {
            val movieId = it.arguments?.getInt("movieId") ?: 0
            DetalhesScreen(movieId = movieId)
        }
    }
}