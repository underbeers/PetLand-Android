package com.petland.app.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.petland.app.ui.theme.PetlandTheme
import com.petland.app.ui.theme.White

@Preview
@Composable
private fun DefaultButtonPreview() {
    PetlandTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            DefaultButton(
                text = "Cледующий шаг",
                onClick = {},
            )
            DefaultButton(
                text = "Cледующий шаг",
                enabled = false,
                onClick = {},
            )
        }
    }
}

@Composable
fun DefaultButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = false,
    onClick: () -> Unit = {},
    icon: ImageVector? = null,
    color: Color = PetlandTheme.colors.primary,
) {
    Button(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = color,
            contentColor = White,
        ),
        enabled = enabled,
        elevation = ButtonDefaults.elevation(
            defaultElevation = 8.dp,
            pressedElevation = 0.dp,
            disabledElevation = 0.dp,
        ),
        contentPadding = PaddingValues(
            vertical = 12.dp,
            horizontal = if (icon != null) 12.dp else 6.dp
        ),
        onClick = onClick,
    ) {
        Text(
            text = text,
            style = PetlandTheme.typography.bigTitle,
        )
        if (icon != null) {
            Icon(
                modifier = Modifier.padding(horizontal = 4.dp),
                imageVector = icon,
                contentDescription = null, tint = White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultOutlinedButtonPreview() {
    PetlandTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            DefaultOutlinedButton(
                text = "Button",
                onClick = {},
            )
            DefaultOutlinedButton(
                text = "Button",
                enabled = false,
                onClick = {},
            )
        }
    }
}

@Composable
fun DefaultOutlinedButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = false,
    onClick: () -> Unit,
    isCounterEnabled: Boolean = false,
    seconds: String? = null,
) {
    Column(modifier = modifier) {
        OutlinedButton(
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(
                width = 2.dp,
                color = when {
                    enabled -> PetlandTheme.colors.primary
                    else -> PetlandTheme.colors.textLight
                },
            ),
            colors = ButtonDefaults.outlinedButtonColors(
                backgroundColor = White,
                contentColor = PetlandTheme.colors.text,
                disabledContentColor = PetlandTheme.colors.textLight,
            ),
            enabled = enabled,
            elevation = ButtonDefaults.elevation(
                defaultElevation = 8.dp,
                pressedElevation = 0.dp,
                disabledElevation = 0.dp,
            ),
            contentPadding = PaddingValues(vertical = 12.dp, horizontal = 16.dp),
            onClick = onClick,
        ) {
            Text(
                text = text,
                style = PetlandTheme.typography.outlinedButtonTitle,
                textAlign = TextAlign.Center
            )
        }
        if (isCounterEnabled) {
            Text(
                modifier = Modifier
                    .padding(top = 4.dp),
                text = seconds ?: "",
                textAlign = TextAlign.Center,
                color = PetlandTheme.colors.primary,
                style = PetlandTheme.typography.outlinedButtonTitle,
            )
        }
    }
}
