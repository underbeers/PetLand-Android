package com.petland.app.ui.components

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import com.petland.app.ui.theme.PetlandTheme

@Composable
fun CustomDialog(
    title: String,
    message: String,
    isDialogOpen: Boolean,
    onDialogExit: () -> Unit,
) {
    if (isDialogOpen) {
        AlertDialog(
            onDismissRequest = onDialogExit,
            title = {
                Text(text = title, textAlign = TextAlign.Center)
            },
            text = {
                Text(
                    text = message,
                    style = PetlandTheme.typography.smallText,
                    color = PetlandTheme.colors.textLight,
                )
            },
            confirmButton = {}
        )
    }
}