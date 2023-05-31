package com.petland.app.features.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.petland.app.ext.popUpToTop
import com.petland.app.navigation.Navigation
import com.petland.app.navigation.Screen
import com.petland.app.navigation.getTitleOfScreen
import com.petland.app.ui.components.BottomNavigationUI
import com.petland.app.ui.components.TopBar
import com.petland.app.util.getBottomBarState
import com.petland.app.util.getTopBarState

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }
    val topBarState = rememberSaveable { mutableStateOf(true) }
    val mainViewModel = hiltViewModel<MainViewModel>()
    val state = mainViewModel.state.collectAsState()
    topBarState.value = getTopBarState(navController)
    bottomBarState.value = getBottomBarState(navController)
    LaunchedEffect(key1 = mainViewModel.effect) {
        mainViewModel.effect.collect { effect ->
            when (effect) {
                MainEffect.NavigateBack -> navController.navigateUp()
                MainEffect.NavigateToProfile -> navController.navigate(Screen.Profile.route) { popUpToTop(navController) }
                MainEffect.NavigateToPetAdd -> navController.navigate(Screen.PetAdd.route)
            }
        }
    }
    Scaffold(
        topBar = {
            TopBar(
                isNavigationBackEnabled = bottomBarState.value.not(),
                title = getTitleOfScreen(navController),
                onNavigateBack = mainViewModel::onNavigateBack,
                onLogoutClicked = mainViewModel::onLogoutClicked,
                isVisible = topBarState,
                onPetAddClick = mainViewModel::onPetAddClick
            )
        },
        bottomBar = {
            BottomNavigationUI(navController, bottomBarState)
        }
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Navigation(navController, Modifier.padding(it), state.value.isAuthorized)
        }
    }
}