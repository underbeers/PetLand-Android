package com.petland.app.features.sign_up.content

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.petland.app.R
import com.petland.app.features.sign_up.SignUpState
import com.petland.app.ui.components.DefaultOutlinedButton
import com.petland.app.ui.components.DefaultTextField
import com.petland.app.util.declension_of_number.NumberForm
import com.petland.app.util.getProperFormOfNumber
import com.petland.app.util.validator.Acceptance

@Composable
fun SignUpSecondStepContent(
    state: SignUpState,
    onEmailChange: (String) -> Unit,
    onSendCode: () -> Unit,
    onSendCodeChange: (String) -> Unit,
) {
    val focusManager = LocalFocusManager.current

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
        DefaultTextField(
            modifier = Modifier.padding(
                start = 30.dp,
                end = 30.dp,
                top = 15.dp,
                bottom = 5.dp
            ),
            value = state.email.value,
            onValueChange = onEmailChange,
            placeholder = stringResource(id = R.string.registration_screen_email),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            isError = state.email.isNotAccepted,
            error = when (state.email.acceptance) {
                Acceptance.INCORRECT_EMAIL -> stringResource(id = R.string.login_screen_email_error)
                else -> null
            },
            isSuccessHintEnabled = state.isEmailVerified,
            successHint = stringResource(id = R.string.registration_screen_email_verified)
        )
        DefaultOutlinedButton(
            modifier = Modifier
                .padding(vertical = 5.dp),
            text = stringResource(id = R.string.registration_screen_send_code),
            enabled = state.isPossibleToSendCode,
            onClick = onSendCode,
            isCounterEnabled = state.isCountDownStarted,
            seconds = when (getProperFormOfNumber(state.seconds)) {
                NumberForm.ONE -> stringResource(
                    id = R.string.registration_screen_countdown_ends_with_one,
                    state.seconds
                )
                NumberForm.FROM_TWO_TO_FOUR -> stringResource(
                    id = R.string.registration_screen_countdown_ends_from_two_to_four,
                    state.seconds
                )
                NumberForm.ANY -> stringResource(
                    id = R.string.registration_screen_countdown_ends_with_other_digits,
                    state.seconds
                )
            }
        )
        DefaultTextField(
            modifier = Modifier.padding(horizontal = 30.dp, vertical = 10.dp),
            placeholder = stringResource(id = R.string.registration_screen_receive_code),
            value = state.sendCode.value,
            onValueChange = onSendCodeChange,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.NumberPassword, imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            isError = state.sendCode.isNotAccepted,
            error = when (state.sendCode.acceptance) {
                Acceptance.EMPTY -> stringResource(id = R.string.text_field_empty_input)
                Acceptance.AT_LEAST_SIX_DIGITS_IN_SENT_CODE -> stringResource(id = R.string.registration_screen_send_code_length)
                Acceptance.MISMATCH -> stringResource(id = R.string.registration_screen_receive_code_mismatch)
                else -> null
            },
        )
    }
}