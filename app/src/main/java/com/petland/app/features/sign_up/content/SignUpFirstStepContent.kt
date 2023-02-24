package com.petland.app.features.sign_up.content

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
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
import com.petland.app.ui.components.DefaultButton
import com.petland.app.ui.components.DefaultOutlinedButton
import com.petland.app.ui.components.DefaultTextField
import com.petland.app.ui.theme.PetlandTheme
import com.petland.app.util.declension_of_number.NumberForm
import com.petland.app.util.getProperFormOfNumber
import com.petland.app.util.validator.Acceptance
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SignUpFirstStepContent(
    state: SignUpState,
    onFirstNameChange: (String) -> Unit,
    onLastNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onSendCode: () -> Unit,
    onNavigateToSecondStep: () -> Unit,
    onNavigateToLogIn: () -> Unit,
    onSendCodeChange: (String) -> Unit,
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
            modifier = Modifier.padding(horizontal = 30.dp, vertical = 10.dp),
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
                .padding(horizontal = 30.dp, vertical = 10.dp)
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
                keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) }),
            isError = state.secondName.isNotAccepted,
            error = when (state.secondName.acceptance) {
                Acceptance.EMPTY -> stringResource(id = R.string.text_field_empty_input)
                Acceptance.UNSUPPORTED_CHAR -> stringResource(id = R.string.text_field_unsupported_char)
                Acceptance.DIGITS_UNSUPPORTED -> stringResource(id = R.string.text_field_digit_is_not_allowed)
                else -> null
            },
        )
        DefaultTextField(
            modifier = Modifier
                .padding(horizontal = 30.dp, vertical = 10.dp)
                .bringIntoViewRequester(BringIntoViewRequester())
                .onFocusEvent {
                    if (it.isFocused) {
                        scope.launch {
                            bringIntoViewRequester.bringIntoView()
                        }
                    }
                },
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
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            DefaultOutlinedButton(
                modifier = Modifier.width(120.dp),
                text = stringResource(id = R.string.registration_screen_send_code),
                enabled = state.isPossibleToSendCode,
                onClick = onSendCode,
                isCounterEnabled = state.isCountDownStarted,
                seconds = when(getProperFormOfNumber(state.seconds)){
                    NumberForm.ONE -> stringResource(id = R.string.registration_screen_countdown_ends_with_one, state.seconds)
                    NumberForm.FROM_TWO_TO_FOUR -> stringResource(id = R.string.registration_screen_countdown_ends_from_two_to_four, state.seconds)
                    NumberForm.ANY -> stringResource(id = R.string.registration_screen_countdown_ends_with_other_digits, state.seconds)
                }
            )
            DefaultTextField(
                modifier = Modifier
                    .bringIntoViewRequester(BringIntoViewRequester())
                    .onFocusEvent {
                        if (it.isFocused) {
                            scope.launch {
                                bringIntoViewRequester.bringIntoView()
                            }
                        }
                    },
                value = state.sendCode.value,
                onValueChange = onSendCodeChange,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.NumberPassword, imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                error = when (state.sendCode.acceptance) {
                    Acceptance.EMPTY -> stringResource(id = R.string.text_field_empty_input)
                    Acceptance.AT_LEAST_SIX_DIGITS_IN_SENT_CODE -> stringResource(id = R.string.registration_screen_send_code_length)
                    else -> null
                },
            )
        }
        DefaultButton(
            modifier = Modifier
                .padding(vertical = 5.dp)
                .width(200.dp),
            text = stringResource(id = R.string.registration_screen_next_step),
            enabled = state.isAllowedMoveToNextStep,
            onClick = onNavigateToSecondStep
        )
        Text(
            modifier = Modifier.padding(start = 30.dp, end = 30.dp, top = 10.dp, bottom = 2.dp),
            text = stringResource(id = R.string.registration_screen_account_already_exists),
            style = PetlandTheme.typography.smallText,
            color = PetlandTheme.colors.textLight,
        )
        Text(
            modifier = Modifier
                .padding(horizontal = 30.dp, vertical = 2.dp)
                .clickable { onNavigateToLogIn() },
            text = stringResource(id = R.string.registration_screen_login),
            style = PetlandTheme.typography.underlinedText,
            color = PetlandTheme.colors.textLight,
        )
    }
}