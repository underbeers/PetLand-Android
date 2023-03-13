package com.petland.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.petland.app.ui.theme.PetlandTheme
import com.petland.app.ui.theme.White

@Preview(showBackground = true)
@Composable
private fun DefaultTextFieldPreview() {
    PetlandTheme {
        DefaultTextField(
            modifier = Modifier.padding(16.dp),
            placeholder = "Label",
            value = "",
            isError = false,
            onValueChange = {},
        )
    }
}

@Composable
fun DefaultTextField(
    value: String,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    error: String? = null,
    isError: Boolean = false,
    isSuccessHintEnabled: Boolean = false,
    successHint: String? = null,
    readOnly: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    onValueChange: (String) -> Unit,
    isEnabled: Boolean = true
) {
    Column(
        modifier = modifier,
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(
                    elevation = 5.dp,
                    shape = RoundedCornerShape(16.dp)
                )
                .background(
                    color = White,
                    shape = RoundedCornerShape(16.dp),
                ),
            value = value,
            enabled = isEnabled,
            readOnly = readOnly,
            textStyle = PetlandTheme.typography.outlinedButtonTitle,
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = PetlandTheme.colors.text,
                backgroundColor = White,
                focusedBorderColor = PetlandTheme.colors.secondary,
                unfocusedBorderColor = Color.Transparent,
                errorBorderColor = PetlandTheme.colors.error,
                disabledBorderColor = Color.Transparent,
            ),
            isError = isError,
            shape = RoundedCornerShape(16.dp),
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            placeholder = {
                Text(
                    text = placeholder,
                    style = PetlandTheme.typography.outlinedButtonTitle,
                    color = PetlandTheme.colors.text,
                )
            },
            onValueChange = onValueChange,
        )
        if (isError) {
            Text(
                modifier = Modifier
                    .padding(top = 4.dp)
                    .align(Alignment.End),
                text = error ?: "",
                color = PetlandTheme.colors.error,
                style = PetlandTheme.typography.outlinedButtonTitle,
            )
        } else if (isSuccessHintEnabled) {
            Text(
                modifier = Modifier
                    .padding(top = 4.dp)
                    .align(Alignment.End),
                text = successHint ?: "",
                color = PetlandTheme.colors.secondary,
                style = PetlandTheme.typography.outlinedButtonTitle,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PasswordTextFieldPreview() {
    PetlandTheme {
        PasswordTextField(
            modifier = Modifier.padding(16.dp),
            placeholder = "Label",
            value = "",
            isError = false,
            onValueChange = {},
            onVisibilityChange = {},
            onFocused = {},
        )
    }
}

@Composable
fun PasswordTextField(
    value: String,
    modifier: Modifier = Modifier,
    passwordVisible: Boolean = false,
    error: String? = null,
    isError: Boolean = false,
    placeholder: String = "",
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    onValueChange: (String) -> Unit,
    onVisibilityChange: () -> Unit,
    onFocused: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    when {
        isFocused -> onFocused()
    }
    Column(
        modifier = modifier,
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .shadow(
                    elevation = 5.dp,
                    shape = RoundedCornerShape(16.dp)
                )
                .background(
                    color = White,
                    shape = RoundedCornerShape(16.dp),
                ),
            value = value,
            textStyle = PetlandTheme.typography.outlinedButtonTitle,
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = PetlandTheme.colors.text,
                backgroundColor = White,
                focusedBorderColor = PetlandTheme.colors.secondary,
                unfocusedBorderColor = Color.Transparent,
                errorBorderColor = PetlandTheme.colors.error,
                disabledBorderColor = Color.Transparent,
            ),
            isError = isError,
            shape = RoundedCornerShape(16.dp),
            visualTransformation = if (passwordVisible) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation('●')
            },
            keyboardOptions = keyboardOptions,
            interactionSource = interactionSource,
            keyboardActions = keyboardActions,
            placeholder = {
                Text(
                    text = placeholder,
                    style = PetlandTheme.typography.outlinedButtonTitle,
                    color = PetlandTheme.colors.text,
                )
            },
            trailingIcon = {
                val icon = if (passwordVisible) {
                    Icons.Outlined.Visibility
                } else {
                    Icons.Outlined.VisibilityOff
                }
                IconButton(
                    content = {
                        Icon(imageVector = icon, contentDescription = null)
                    },
                    onClick = onVisibilityChange,
                )
            },
            onValueChange = onValueChange,
        )
        if (error != null) {
            Text(
                modifier = Modifier
                    .padding(top = 4.dp)
                    .align(Alignment.End),
                text = error,
                color = PetlandTheme.colors.error,
                style = PetlandTheme.typography.outlinedButtonTitle,
            )
        }
    }
}

