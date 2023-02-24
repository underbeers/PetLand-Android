package com.petland.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

object PetlandTheme {
    val colors: PetlandColors
        @Composable
        @ReadOnlyComposable
        get() = LocalPetlandColors.current
    val typography: PetlandTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalPetlandTypography.current
}

@Composable
fun PetlandTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {

    val colors = PetlandLightPalette
    val typography = PetlandTypography()

    CompositionLocalProvider(
        LocalPetlandColors provides colors,
        LocalPetlandTypography provides typography,
        content = content,
    )
}