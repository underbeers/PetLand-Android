package com.petland.app.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.petland.app.features.bulletin_board.BulletinBoardScreen
import com.petland.app.features.chat.ChatScreen
import com.petland.app.features.favorites.FavoritesScreen
import com.petland.app.features.log_in.LoginScreen
import com.petland.app.features.pet.edit.PetEditScreen
import com.petland.app.features.pet.info.PetScreen
import com.petland.app.features.profile.ProfileScreen
import com.petland.app.features.rating.RatingScreen
import com.petland.app.features.services.ServicesScreen
import com.petland.app.features.sign_up.SignUpScreen
import com.petland.app.features.specialist.SpecialistScreen
import com.petland.app.features.user_advert.UserAdvertScreen

@Composable
fun Navigation(
    navController: NavHostController, modifier: Modifier = Modifier, isAuthorized: Boolean
) {
    NavHost(
        navController,
        startDestination = Screen.Profile.route,
        modifier
    ) {
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
        composable(Screen.Profile.route) {
            ProfileScreen(navController = navController)
        }
        composable(Screen.Rating.route) {
            RatingScreen()
        }
        composable(Screen.AdvertProfile.route) {
            UserAdvertScreen()
        }
        composable(Screen.PetAdd.route) {
            PetEditScreen()
        }
        composable(Screen.PetEdit.route) {
            PetEditScreen()
        }
        composable(Screen.Specialist.route) {
            SpecialistScreen()
        }
        composable(
            Screen.Pet.route.plus(Screen.Pet.firstObjectPath).plus(Screen.Pet.secondObjectPath),
            arguments = listOf(
                navArgument(Screen.Pet.firstObjectName) { type = NavType.StringType },
                navArgument(Screen.Pet.secondObjectName) { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val userName = backStackEntry.arguments?.getString(Screen.Pet.firstObjectName) ?: ""
            val avatarLink = backStackEntry.arguments?.getString(Screen.Pet.secondObjectName) ?: ""
            PetScreen(navController, userName, avatarLink)
        }
    }
}

@Composable
fun currentRoute(navController: NavController): String {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route?.substringBefore("/") ?: ""
}

@Composable
fun getTitleOfScreen(navController: NavController): String = when (currentRoute(navController)) {
    Screen.Chat.route -> stringResource(Screen.Chat.title)
    Screen.Favorites.route -> stringResource(Screen.Favorites.title)
    Screen.Profile.route -> stringResource(Screen.Profile.title)
    Screen.BulletinBoard.route -> stringResource(Screen.BulletinBoard.title)
    Screen.Services.route -> stringResource(Screen.Services.title)
    Screen.Rating.route -> stringResource(Screen.Rating.title)
    Screen.Pet.route -> stringResource(Screen.Pet.title)
    Screen.AdvertProfile.route -> stringResource(id = Screen.AdvertProfile.title)
    Screen.PetEdit.route -> stringResource(id = Screen.PetEdit.title)
    Screen.PetAdd.route -> stringResource(id = Screen.PetAdd.title)
    Screen.Specialist.route -> stringResource(id = Screen.Specialist.title)
    else -> ""
}

fun getRouteToScreenWithTwoArguments(
    route: String,
    firstArgument: String,
    secondArgument: String
): String = route.plus("/${Uri.encode(firstArgument)}/${Uri.encode(secondArgument)}")


