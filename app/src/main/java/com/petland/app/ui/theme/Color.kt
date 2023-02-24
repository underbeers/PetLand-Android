package com.petland.app.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class PetlandColors(
    val primary: Color,
    val primaryLight: Color,
    val secondary: Color,
    val text: Color,
    val textLight: Color,
    val error: Color
)

//Theme colors
private val primary = Color(0xFFF47932)
private val secondary = Color (0xFF97B14B)
private val text = Color(0xFF4F4F4F)
private val textLight = Color(0xFFAEB9CC)
private val error = Color(0xFFFF5454)
private val primaryLight = Color(0xFFF4B691)

//Additional
val White = Color(0xFFFFFFFF)
val Black = Color(0xFF000000)
val ScreenOpacity = Color(0xBF333333)

val PetlandLightPalette = PetlandColors(
    primary = primary,
    primaryLight = primaryLight,
    secondary = secondary,
    text = text,
    textLight = textLight,
    error = error
)

val LocalPetlandColors = staticCompositionLocalOf<PetlandColors> {
    error("No colors provided")
}
