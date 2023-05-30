package com.petland.app.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import com.petland.app.R


private val mulish = FontFamily(
    Font(R.font.mulish_bold, FontWeight.Bold),
    Font(R.font.mulish_regular, FontWeight.Normal),
    Font(R.font.mulish_semibold, FontWeight.SemiBold),
)
data class PetlandTypography(
    val bigTitle: TextStyle = TextStyle(
        fontFamily = mulish,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
    ),
    val text: TextStyle = TextStyle(
        fontFamily = mulish,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    val outlinedButtonTitle: TextStyle = TextStyle(
        fontFamily = mulish,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp
    ),
    val underlinedText: TextStyle = TextStyle(
        fontFamily = mulish,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        textDecoration = TextDecoration.Underline
    ),
    val defaultButtonText: TextStyle = TextStyle(
        fontFamily = mulish,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    ),
    val smallText: TextStyle = TextStyle(
        fontFamily = mulish,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    val navigationTitle: TextStyle = TextStyle(
        fontFamily = mulish,
        fontWeight = FontWeight.SemiBold,
        fontSize = 10.sp
    ),
    val suggestionTitle: TextStyle = TextStyle(
        fontFamily = mulish,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    val header: TextStyle = TextStyle(
        fontFamily = mulish,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    )
)

val LocalPetlandTypography = staticCompositionLocalOf<PetlandTypography> {
    error("No typography provided")
}