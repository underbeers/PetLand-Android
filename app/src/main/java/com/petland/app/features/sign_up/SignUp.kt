package com.petland.app.features.sign_up

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.petland.app.R

@Composable
fun SignUpScreen(
    navController: NavController,
) {
    val signUpViewModel = hiltViewModel<SignUpViewModel>()
    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(R.drawable.background),
        contentDescription = null,
        contentScale = ContentScale.FillBounds
    )
}