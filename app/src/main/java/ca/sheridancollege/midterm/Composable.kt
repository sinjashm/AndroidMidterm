package ca.sheridancollege.midterm

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import ca.sheridancollege.midterm.data.User
import ca.sheridancollege.midterm.network.UserApi

@Composable
fun LoadingScreen() =
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }

@Composable
fun ErrorScreen(msg: String) =
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(msg)
    }

@Composable
fun UserList(
    users: List<User>,
    onClick: (String) -> Unit
) {
    LazyColumn {
        items(users) { user -> UserCard(user, onClick) }
    }
}

@Composable
private fun UserCard(
    user: User,
    onClick: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick(user.id) }
    ) {
        Row(Modifier.padding(16.dp)) {
            AsyncImage(
                model = UserApi.IMAGE_BASE + user.image,
                contentDescription = user.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(72.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(Modifier.width(16.dp))
            Column {
                Text(user.name, style = MaterialTheme.typography.titleMedium)
                Text("${user.age} • ${user.gender}", style = MaterialTheme.typography.bodySmall)
                Text(user.location, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailScreen(
    userId: String,
    onBack: () -> Unit
) {
    val vm: UserViewModel = hiltViewModel()
    val user = (vm.uiState as? UserViewModel.UiState.Success)
        ?.users?.firstOrNull { it.id == userId }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(user?.name ?: "User") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        if (user == null) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("User not found")
            }
        } else {
            Column(
                Modifier
                    .padding(padding)
                    .padding(16.dp)
            ) {
                AsyncImage(
                    model = UserApi.IMAGE_BASE + user.image,
                    contentDescription = user.name,
                    modifier = Modifier
                        .size(200.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .align(Alignment.CenterHorizontally)
                )
                Spacer(Modifier.height(24.dp))
                Text("Name: ${user.name}")
                Text("Gender: ${user.gender}")
                Text("Age: ${user.age}")
                Text("Location: ${user.location}")
            }
        }
    }
}
