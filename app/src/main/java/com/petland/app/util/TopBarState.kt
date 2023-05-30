package com.petland.app.util

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.petland.app.navigation.Screen
import com.petland.app.navigation.currentRoute

@Composable
fun getTopBarState(navController: NavController) = when (currentRoute(navController)) {
    Screen.Login.route, Screen.SignUp.route -> false
    else -> true
}