package ca.sheridancollege.midterm

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import ca.sheridancollege.midterm.UserViewModel
import ca.sheridancollege.midterm.*

@Composable
fun UserApp(viewModel: UserViewModel = hiltViewModel()) {
    when (val state = viewModel.uiState) {
        UserViewModel.UiState.Loading    -> LoadingScreen()
        is UserViewModel.UiState.Error   -> ErrorScreen(state.msg)
        is UserViewModel.UiState.Success -> UserList(state.users)
    }
}