package com.example.scenee.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scenee.data.model.User
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val name: String = "",
    val errorMessage: String? = null
)

sealed class NavigationEvent {
    object NavigateToHome : NavigationEvent()
    object NavigateToSignUp : NavigationEvent()
    object NavigateToLogin : NavigationEvent()
}

class LoginViewModel : ViewModel() {

    // Banco de dados em memória para simulação
    private val users = mutableListOf<User>()

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    private val _navigationEvent = MutableSharedFlow<NavigationEvent>()
    val navigationEvent = _navigationEvent.asSharedFlow()

    fun onEmailChange(email: String) {
        _uiState.update { it.copy(email = email) }
    }

    fun onPasswordChange(password: String) {
        _uiState.update { it.copy(password = password) }
    }

    fun onNameChange(name: String) {
        _uiState.update { it.copy(name = name) }
    }

    fun login() {
        viewModelScope.launch {
            val state = _uiState.value
            val user = users.find { it.email == state.email && it.password == state.password }
            if (user != null) {
                _navigationEvent.emit(NavigationEvent.NavigateToHome)
            } else {
                _uiState.update { it.copy(errorMessage = "Email ou senha inválidos") }
            }
        }
    }

    fun signUp() {
        viewModelScope.launch {
            val state = _uiState.value
            if (users.any { it.email == state.email }) {
                _uiState.update { it.copy(errorMessage = "Este email já está em uso") }
            } else {
                users.add(User(name = state.name, email = state.email, password = state.password))
                _navigationEvent.emit(NavigationEvent.NavigateToLogin) // Leva para tela de login após cadastro
            }
        }
    }

    fun clearError() {
        _uiState.update { it.copy(errorMessage = null) }
    }

    fun onNavigateToSignUpClicked() {
        viewModelScope.launch {
            _navigationEvent.emit(NavigationEvent.NavigateToSignUp)
        }
    }

    fun onNavigateToLoginClicked() {
        viewModelScope.launch {
            _navigationEvent.emit(NavigationEvent.NavigateToLogin)
        }
    }
}