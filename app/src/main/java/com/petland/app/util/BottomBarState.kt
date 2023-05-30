package com.petland.app.util

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.petland.app.navigation.Screen
import com.petland.app.navigation.currentRoute

@Composable
fun getBottomBarState(navController: NavController) = when (currentRoute(navController)) {
    Screen.BulletinBoard.route, Screen.Services.route, Screen.Favorites.route, Screen.Chat.route, Screen.Profile.route -> {
        true
    }
    else -> {
        false
    }
}