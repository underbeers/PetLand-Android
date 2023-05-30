package com.petland.app.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Logout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.petland.app.ui.theme.PetlandTheme

@Composable
fun TopBar(
    isNavigationBackEnabled: Boolean,
    title: String,
    isVisible: MutableState<Boolean>,
    isLogoutButtonVisible: Boolean = false,
    onLogoutClicked: () -> Unit,
    onNavigateBack: () -> Unit,
) {
    AnimatedVisibility(
        visible = isVisible.value,
        enter = slideInVertically(initialOffsetY = { -it }),
        exit = slideOutVertically(targetOffsetY = { -it }),
    ) {
        TopAppBar(
            title = {
                Text(
                    modifier = Modifier.padding(vertical = 5.dp),
                    text = title,
                    style = PetlandTheme.typography.header,
                )
            },
            navigationIcon = if (isNavigationBackEnabled) {
                {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Filled.ArrowBack, null)
                    }
                }
            } else null,
            backgroundColor = PetlandTheme.colors.background,
            actions = {
                if (isNavigationBackEnabled.not() && isLogoutButtonVisible) {
                    IconButton(onClick = onLogoutClicked) {
                        Icon(
                            modifier = Modifier.size(20.dp),
                            imageVector = Icons.Filled.Logout,
                            contentDescription = null
                        )
                    }
                }
            }
        )
    }
}