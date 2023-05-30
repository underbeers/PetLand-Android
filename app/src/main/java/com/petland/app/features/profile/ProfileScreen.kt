package com.petland.app.features.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.petland.app.navigation.Screen
import com.petland.app.ui.components.Loader
import com.petland.app.util.ProfileType

@Composable
fun ProfileScreen(navController: NavController) {
    val profileViewModel = hiltViewModel<ProfileViewModel>()
    val state by profileViewModel.state.collectAsState()
    ProfileContent(
        state = state,
        onAuthorize = profileViewModel::onAuthorize,
        onPetsClick = profileViewModel::onPetsClick,
        onAdvertClick = profileViewModel::onAdvertClick,
        onRatingClick = profileViewModel::onRatingClick,
    )
    LaunchedEffect(key1 = profileViewModel.effect) {
        profileViewModel.effect.collect { effect ->
            when (effect) {
                is ProfileEffect.NavigateToAuthorization -> navController.navigate(Screen.Login.route)
                is ProfileEffect.NavigateToRating -> navController.navigate(Screen.Rating.route)
                is ProfileEffect.NavigateToPets -> navController.navigate(Screen.Pet.route)
                is ProfileEffect.NavigateToUserAdverts -> navController.navigate(Screen.AdvertProfile.route)
            }
        }
    }
}

@Composable
fun ProfileContent(
    state: ProfileState,
    onAuthorize: () -> Unit,
    onPetsClick: () -> Unit,
    onAdvertClick: () -> Unit,
    onRatingClick: () -> Unit,
) {
    if (state.isLoading) {
        Loader(isScreenOpacityEnabled = false)
    } else {
        when (state.profileType) {
            ProfileType.UNAUTHORIZED -> {
                UnauthorizedUserContent(onAuthorize)
            }

            ProfileType.USER_PROFILE -> {
                UserProfileContent(
                    state = state,
                    onPetsClick = onPetsClick,
                    onRatingClick = onRatingClick,
                    onAdvertClick = onAdvertClick
                )
            }
        }
    }
}