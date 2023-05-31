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
    val firstObjectName: String = "",
    val firstObjectPath: String = "",
    val secondObjectName: String = "",
    val secondObjectPath: String = ""
) {
    object Login : Screen("login_screen")
    object SignUp: Screen("sign_up")

    object Specialist: Screen("specialist", title = R.string.specialist_screen_title)

    object Rating: Screen("rating", title = R.string.profile_screen_rating)

    object AdvertProfile: Screen("advert_profile", title = R.string.pet_user_advert_screen)

    object PetEdit: Screen("pet_edit",  title = R.string.pet_screen_title_edit)
    object PetAdd: Screen("pet_add", title = R.string.pet_screen_title_add)

    object Pet: Screen(
        route = "pet",
        title = R.string.profile_screen_pets_title,
        firstObjectName = "userInfo",
        firstObjectPath = "/{userInfo}",
        secondObjectName = "avatarLink",
        secondObjectPath = "/{avatarLink}")

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