package com.slyrand.example.ui.user.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.slyrand.domain.user.model.User
import org.koin.androidx.compose.get

@ExperimentalFoundationApi
@ExperimentalCoilApi
@Composable
fun UserListScreen(
    onClick: (User) -> Unit,
    viewModel: UsersViewModel = get()
) {

    val state = viewModel.state.collectAsState()

    state.value.users.fold(
        { },
        { users ->  UserList(users = users) }
    )
}

@ExperimentalFoundationApi
@Composable
fun UserList(users: List<User>) {
    LazyVerticalGrid(
        cells = GridCells.Adaptive(120.dp),
        contentPadding = PaddingValues(2.dp)
    ) {
        items(users) { user ->
            UserItem(user, Modifier.padding(2.dp))
        }
    }
}

@Composable
fun UserItem(user: User, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .height(160.dp)
                .width(120.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(user.picture)
                    .crossfade(1000)
                    .build(),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .height(40.dp)
                .width(120.dp)
                .background(MaterialTheme.colors.secondary)
                .padding(4.dp)
        ) {
            Text(text = user.firstName)
        }
    }
}
