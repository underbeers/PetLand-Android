package com.petland.app.features.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.petland.app.R
import com.petland.app.navigation.Screen
import com.petland.app.ui.components.DefaultButton
import com.petland.app.ui.theme.PetlandTheme
import com.petland.app.util.ProfileType

@Composable
fun ProfileScreen(navController: NavController) {
    val profileViewModel = hiltViewModel<ProfileViewModel>()
    val state by profileViewModel.state.collectAsState()
    ProfileContent(
        state = state,
        onAuthorize = profileViewModel::onAuthorize
    )
    LaunchedEffect(key1 = profileViewModel.effect) {
        profileViewModel.effect.collect { effect ->
            if (effect is ProfileEffect.NavigateToAuthorization) navController.navigate(Screen.Login.route)
        }
    }
}

@Composable
fun ProfileContent(
    state: ProfileState,
    onAuthorize: () -> Unit
) {
    when (state.profileType) {
        ProfileType.UNAUTHORIZED -> {
            UnauthorizedUserContent(onAuthorize)
        }
        ProfileType.USER_PROFILE -> {

        }
        ProfileType.OTHER_USER_PROFILE -> {

        }
    }
}

@Composable
fun UnauthorizedUserContent(
    onAuthorize: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 120.dp, bottom = 20.dp),
            text = stringResource(id = R.string.profile_screen_unauthorized),
            style = PetlandTheme.typography.suggestionTitle,
            textAlign = TextAlign.Center
        )
        DefaultButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.profile_screen_authorize_button),
            enabled = true,
            onClick = onAuthorize
        )
    }
}

@Composable
fun UserProfileContent() {

}

@Composable
fun OtherUserProfile() {

}