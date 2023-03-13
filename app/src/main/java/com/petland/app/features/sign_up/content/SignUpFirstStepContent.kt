package com.petland.app.features.sign_up.content

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.petland.app.R
import com.petland.app.features.sign_up.SignUpState
import com.petland.app.ui.components.DefaultTextField
import com.petland.app.util.validator.Acceptance
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SignUpFirstStepContent(
    state: SignUpState,
    onFirstNameChange: (String) -> Unit,
    onLastNameChange: (String) -> Unit,
) {
    val focusManager = LocalFocusManager.current
    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    val scope = rememberCoroutineScope()
    val scrollState = rememberScrollState()
    Column(modifier = Modifier
        .fillMaxSize()
        .pointerInput(Unit) {
            detectTapGestures(
                onTap = { focusManager.clearFocus() },
            )
        }
        .verticalScroll(state = scrollState),
        horizontalAlignment = Alignment.CenterHorizontally) {
        DefaultTextField(
            modifier = Modifier.padding(horizontal = 30.dp, vertical = 15.dp),
            value = state.firstName.value,
            onValueChange = onFirstNameChange,
            placeholder = stringResource(id = R.string.registration_screen_first_name),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) }),
            isError = state.firstName.isNotAccepted,
            error = when (state.firstName.acceptance) {
                Acceptance.EMPTY -> stringResource(id = R.string.text_field_empty_input)
                Acceptance.UNSUPPORTED_CHAR -> stringResource(id = R.string.text_field_unsupported_char)
                Acceptance.DIGITS_UNSUPPORTED -> stringResource(id = R.string.text_field_digit_is_not_allowed)
                else -> null
            }
        )
        DefaultTextField(
            modifier = Modifier
                .padding(horizontal = 30.dp, vertical = 20.dp)
                .bringIntoViewRequester(BringIntoViewRequester())
                .onFocusEvent {
                    if (it.isFocused) {
                        scope.launch {
                            bringIntoViewRequester.bringIntoView()
                        }
                    }
                },
            value = state.secondName.value,
            onValueChange = onLastNameChange,
            placeholder = stringResource(id = R.string.registration_screen_last_name),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text, imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            isError = state.secondName.isNotAccepted,
            error = when (state.secondName.acceptance) {
                Acceptance.EMPTY -> stringResource(id = R.string.text_field_empty_input)
                Acceptance.UNSUPPORTED_CHAR -> stringResource(id = R.string.text_field_unsupported_char)
                Acceptance.DIGITS_UNSUPPORTED -> stringResource(id = R.string.text_field_digit_is_not_allowed)
                else -> null
            },
        )
    }
}