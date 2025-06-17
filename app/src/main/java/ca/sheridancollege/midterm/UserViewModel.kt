package ca.sheridancollege.midterm

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.sheridancollege.midterm.data.User
import ca.sheridancollege.midterm.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repo: UserRepository
) : ViewModel() {

    sealed interface UiState {
        object Loading : UiState
        data class Success(val users: List<User>) : UiState
        data class Error(val msg: String) : UiState
    }

    var uiState by mutableStateOf<UiState>(UiState.Loading)
        private set

    init { fetch() }

    private fun fetch() = viewModelScope.launch {
        uiState = try {
            UiState.Success(repo.loadUsers())
        } catch (e: Exception) {
            UiState.Error(e.localizedMessage ?: "Unknown error")
        }
    }
}