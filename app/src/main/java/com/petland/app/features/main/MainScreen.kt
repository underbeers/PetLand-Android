package com.petland.app.features.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
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
    val topBarState = rememberSaveable{ mutableStateOf(true) }
    val mainViewModel = hiltViewModel<MainViewModel>()
    topBarState.value = getTopBarState(navController)
    bottomBarState.value = getBottomBarState(navController)
    LaunchedEffect(key1 = mainViewModel.effect) {
        mainViewModel.effect.collect { effect ->
            when (effect) {
                MainEffect.NavigateBack -> navController.navigateUp()
                MainEffect.NavigateToSettings -> navController.navigate(Screen.SettingsScreen.route)
            }
        }
    }
    Scaffold(
        topBar = {
            TopBar(
                isNavigationBackEnabled = bottomBarState.value.not(),
                title = getTitleOfScreen(navController),
                onNavigateBack = mainViewModel::onNavigateBack,
                onSettingsClicked = mainViewModel::onSettingsClicked,
                isVisible = topBarState
            )
        },
        bottomBar = {
            BottomNavigationUI(navController, bottomBarState)
        }
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Navigation(navController, Modifier.padding(it))
            Column {
            }
        }
    }
}