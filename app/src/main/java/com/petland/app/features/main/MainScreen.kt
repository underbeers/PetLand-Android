package com.petland.app.features.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.petland.app.navigation.Navigation
import com.petland.app.ui.components.BottomNavigationUI
import com.petland.app.util.getBottomBarState

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }
    bottomBarState.value = getBottomBarState(navController)
    Scaffold(
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