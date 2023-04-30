package com.petland.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.petland.app.features.bulletin_board.BulletinBoardScreen
import com.petland.app.features.chat.ChatScreen
import com.petland.app.features.favorites.FavoritesScreen
import com.petland.app.features.log_in.LoginScreen
import com.petland.app.features.profile.ProfileScreen
import com.petland.app.features.services.ServicesScreen
import com.petland.app.features.sign_up.SignUpScreen

@Composable
fun Navigation(
    navController: NavHostController, modifier: Modifier = Modifier
) {
    NavHost(navController, startDestination = Screen.Profile.route, modifier) {
        composable(Screen.Login.route) {
            LoginScreen(navController)
        }
        composable(Screen.SignUp.route) {
            SignUpScreen(navController)
        }
        composable(Screen.BulletinBoard.route) {
            BulletinBoardScreen(navController)
        }
        composable(Screen.Services.route) {
            ServicesScreen(navController)
        }
        composable(Screen.Favorites.route) {
            FavoritesScreen()
        }
        composable(Screen.Chat.route) {
            ChatScreen(navController)
        }
        composable(Screen.Profile.route){
            ProfileScreen(navController = navController)
        }
    }
}

@Composable
fun currentRoute(navController: NavController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route?.substringBeforeLast("/")
}