package com.petland.app.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxColors
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.petland.app.ui.theme.PetlandTheme

@Composable
fun TextWithCheckBox(
    modifier: Modifier,
    text: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = customCheckBoxColors()
        )
        Text(
            modifier = Modifier,
            text = text,
            style = PetlandTheme.typography.smallText,
            color = PetlandTheme.colors.textLight,
        )
    }
}

@Composable
fun customCheckBoxColors(): CheckboxColors {
    return CheckboxDefaults.colors(
        checkedColor = PetlandTheme.colors.primary,
    )
}