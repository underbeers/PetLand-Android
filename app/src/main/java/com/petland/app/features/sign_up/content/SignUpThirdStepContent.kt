package com.petland.app.features.sign_up.content

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import com.petland.app.ui.components.PasswordTextField
import com.petland.app.ui.components.TextWithCheckBox
import com.petland.app.util.validator.Acceptance
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SignUpThirdStepContent(
    state: SignUpState,
    onPasswordChange: (String) -> Unit,
    onRepeatedPasswordChange: (String) -> Unit,
    onPasswordVisibilityChange: () -> Unit,
    onRepeatedPasswordVisibilityChange: () -> Unit,
    onCheckedChange: (Boolean) -> Unit,
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
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        PasswordTextField(modifier = Modifier
            .padding(start = 30.dp, end = 30.dp, top = 10.dp)
            .bringIntoViewRequester(BringIntoViewRequester())
            .onFocusEvent {
                if (it.isFocused) {
                    scope.launch {
                        bringIntoViewRequester.bringIntoView()
                    }
                }
            },
            value = state.password.value,
            onValueChange = onPasswordChange,
            onVisibilityChange = onPasswordVisibilityChange,
            passwordVisible = state.isPasswordVisible,
            placeholder = stringResource(R.string.registration_screen_password_create),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onDone = { focusManager.moveFocus(FocusDirection.Down) }),
            isError = state.password.isNotAccepted,
            error = when (state.password.acceptance) {
                Acceptance.LENGTH_SHORT -> stringResource(id = R.string.password_length_short)
                Acceptance.AT_LEAST_ONE_CAPITAL_LETTER -> stringResource(id = R.string.password_at_least_one_capital_letter)
                Acceptance.AT_LEAST_ONE_SPECIAL_SYMBOL -> stringResource(id = R.string.password_at_least_one_special_symbol)
                Acceptance.AT_LEAST_ONE_DIGIT -> stringResource(id = R.string.password_at_least_one_digit)
                else -> null
            },
            onFocused = {}
        )
        PasswordTextField(modifier = Modifier
            .padding(horizontal = 30.dp, vertical = 10.dp)
            .bringIntoViewRequester(bringIntoViewRequester)
            .onFocusEvent {
                if (it.isFocused) {
                    scope.launch {
                        bringIntoViewRequester.bringIntoView()
                    }
                }
            },
            value = state.repeatPassword.value,
            onValueChange = onRepeatedPasswordChange,
            onVisibilityChange = onRepeatedPasswordVisibilityChange,
            passwordVisible = state.isRepeatPasswordVisible,
            placeholder = stringResource(R.string.registration_screen_password_confirmation),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            isError = state.repeatPassword.isNotAccepted,
            error = when (state.repeatPassword.acceptance) {
                Acceptance.MISMATCH -> stringResource(id = R.string.password_mismatch)
                else -> null
            },
            onFocused = {}
        )
        TextWithCheckBox(
            modifier = Modifier.padding(vertical = 5.dp, horizontal = 30.dp),
            text = stringResource(id = R.string.registration_screen_term_of_use),
            checked = state.isCheckBoxChecked,
            onCheckedChange = onCheckedChange
        )
    }
}