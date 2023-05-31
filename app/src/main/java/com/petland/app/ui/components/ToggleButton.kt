package com.petland.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.petland.app.ui.theme.PetlandTheme

@Composable
fun ToggleButtonCustom(
    service: String,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    onItemSelect: (String) -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    Text(
        modifier = modifier
            .background(
                color = when {
                    isSelected -> PetlandTheme.colors.secondary
                    else -> PetlandTheme.colors.background
                }
            )
            .border(
                width = 1.dp,
                color = PetlandTheme.colors.secondary
            )
            .padding(vertical = 16.dp)
            .clickable(
                interactionSource = interactionSource,
                indication = rememberRipple(),
                onClick = { onItemSelect(service) },
            ),
        text = service,
        color = when {
            isSelected -> PetlandTheme.colors.background
            else -> PetlandTheme.colors.secondary
        },
        style = PetlandTheme.typography.text,
        textAlign = TextAlign.Center,
        maxLines = 1,
        overflow = TextOverflow.Clip,
    )
}