package ca.sheridancollege.midterm

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument


@Composable
fun UserApp() {
    val nav = rememberNavController()
    NavHost(navController = nav, startDestination = "list") {

        composable("list") {
            val vm: UserViewModel = hiltViewModel()
            when (val state = vm.uiState) {
                is UserViewModel.UiState.Success -> UserList(
                    users = state.users,
                    onClick = { id -> nav.navigate("detail/$id") }
                )
                is UserViewModel.UiState.Error   -> ErrorScreen(state.msg)
                UserViewModel.UiState.Loading    -> LoadingScreen()
            }
        }

        composable(
            "detail/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("id")!!
            UserDetailScreen(userId, nav::navigateUp)
        }
    }
}