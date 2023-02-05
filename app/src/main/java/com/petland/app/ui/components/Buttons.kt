package com.petland.app.ui.components
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    enabled: Boolean = true,
    onClick: () -> Unit = {},
) {
    Button(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = when {
                !enabled -> PetlandTheme.colors.primaryLight
                else -> PetlandTheme.colors.primary
            },
            contentColor = White,
        ),
        enabled = enabled,
        elevation = ButtonDefaults.elevation(
            defaultElevation = 8.dp,
            pressedElevation = 0.dp,
            disabledElevation = 0.dp,
        ),
        contentPadding = PaddingValues(vertical = 12.dp),
        onClick = onClick,
    ) {
        Text(
            text = text,
            style = PetlandTheme.typography.defaultButtonText
        )
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
    enabled: Boolean = true,
    isNavigateBack: Boolean = false,
    onClick: () -> Unit,
) {
    OutlinedButton(
        modifier = modifier,
        shape = RoundedCornerShape(24.dp),
        border = BorderStroke(
            width = 1.dp,
            color = when {
                isNavigateBack -> PetlandTheme.colors.primary
                enabled -> PetlandTheme.colors.secondary
                else -> PetlandTheme.colors.textLight
            },
        ),
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = Color.Transparent,
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
        )
    }
}
