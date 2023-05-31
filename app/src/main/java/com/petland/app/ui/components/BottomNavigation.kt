package com.petland.app.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavController
import com.petland.app.ext.popUpDestinationAndNavigate
import com.petland.app.navigation.Screen
import com.petland.app.navigation.currentRoute
import com.petland.app.ui.theme.PetlandLightPalette
import com.petland.app.ui.theme.PetlandTheme
import com.petland.app.ui.theme.White

@Composable
fun BottomNavigationUI(navController: NavController, isVisible: MutableState<Boolean>) {
    AnimatedVisibility(
        visible = isVisible.value,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it }),
    ) {
        BottomNavigation(
            backgroundColor = White) {
            val items = listOf(
                Screen.BulletinBoard,
                Screen.Services,
                Screen.Favorites,
                Screen.Chat,
                Screen.Profile
            )
            items.forEach { item ->
                BottomNavigationItem(
                    label = {
                        Text(
                            text = stringResource(id = item.title),
                            style = PetlandTheme.typography.navigationTitle,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    selected = currentRoute(navController) == item.route,
                    icon = item.navIcon,
                    selectedContentColor = PetlandLightPalette.primary,
                    unselectedContentColor = PetlandTheme.colors.textLight,
                    onClick = {
                        navController.popUpDestinationAndNavigate(item.route)
                    })
            }
        }
    }
}