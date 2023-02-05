package com.petland.app.navigation


sealed class Screen(
    val route: String,
    val objectName: String = "",
    val objectPath: String = ""
) {
    object Login : Screen("login_screen")
    object SignUp: Screen("sign_up")
}