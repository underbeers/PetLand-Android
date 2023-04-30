package com.petland.app.navigation

import androidx.annotation.StringRes
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.petland.app.R


sealed class Screen(
    val route: String,
    @StringRes val title: Int = R.string.bulletin_board_screen_title,
    val navIcon: (@Composable () -> Unit) = {
        Icon(
            painter = painterResource(id = R.drawable.ic_chat),
            contentDescription = null
        )
    },
    val objectName: String = "",
    val objectPath: String = ""
) {
    object Login : Screen("login_screen")
    object SignUp: Screen("sign_up")

    object BulletinBoard : Screen("bulletin_board", title = R.string.bulletin_board_screen_title, navIcon = {
        Icon(
            painter = painterResource(id = R.drawable.ic_bulletin_board),
            contentDescription = null,
        )
    })
    object Services : Screen("services", title = R.string.services_screen_title, navIcon = {
        Icon(
            painter = painterResource(id = R.drawable.ic_services),
            contentDescription = null,
        )
    })
    object Favorites : Screen("favorites", title = R.string.favorites_screen_title, navIcon = {
        Icon(
            painter = painterResource(id = R.drawable.ic_favorites),
            contentDescription = null,
        )
    })
    object Chat : Screen("chat", title = R.string.chat_screen_title, navIcon = {
        Icon(
            painter = painterResource(id = R.drawable.ic_chat),
            contentDescription = null,
        )
    })
    object Profile : Screen("profile", title = R.string.profile_screen_title, navIcon = {
        Icon(
            painter = painterResource(id = R.drawable.ic_profile),
            contentDescription = null,
        )
    })


}