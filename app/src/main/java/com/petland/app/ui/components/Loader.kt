package com.petland.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.petland.app.ui.theme.PetlandTheme
import com.petland.app.ui.theme.ScreenOpacity
import com.petland.app.ui.theme.White

@Composable
fun Loader(
    modifier: Modifier = Modifier,
    isScreenOpacityEnabled: Boolean = true,
) {
    val interactionSource = remember { MutableInteractionSource() }
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                color = when {
                    isScreenOpacityEnabled -> ScreenOpacity
                    else -> White
                },
            )
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = {},
            ),
    ) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center),
            color = PetlandTheme.colors.primary,
        )
    }
}