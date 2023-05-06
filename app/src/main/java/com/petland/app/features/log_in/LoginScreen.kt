package com.petland.app.features.log_in

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.petland.app.R
import com.petland.app.data.ApiURL.RESTORE_PASSWORD
import com.petland.app.navigation.Screen
import com.petland.app.ui.components.*
import com.petland.app.ui.theme.PetlandTheme
import com.petland.app.util.validator.Acceptance


@Composable
fun LoginScreen(
    navController: NavController
) {
    val loginViewModel = hiltViewModel<LoginViewModel>()
    val state by loginViewModel.state.collectAsState()
    LoginContent(
        state = state,
        onLoginChange = loginViewModel::onLoginChange,
        onPasswordChange = loginViewModel::onPasswordChange,
        onPasswordVisibilityChange = loginViewModel::onPasswordVisibilityChange,
        onLogin = loginViewModel::onLogIn,
        onSignUp = loginViewModel::onSignUp,
        onCheckedChange = loginViewModel::onCheckedChange,
    )
    LaunchedEffect(loginViewModel.effect) {
        loginViewModel.effect.collect { effect ->
            when (effect) {
                is LoginEffect.NavigateToAccount -> {
                    navController.navigate(Screen.Profile.route)
                }
                is LoginEffect.NavigateToSignUp -> {
                    navController.navigate(Screen.SignUp.route)
                }
            }
        }
    }
}

@Composable
fun LoginContent(
    state: LoginState,
    onLoginChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onPasswordVisibilityChange: () -> Unit,
    onLogin: () -> Unit,
    onSignUp: () -> Unit,
    onCheckedChange: (Boolean) -> Unit,
) {
    val focusManager = LocalFocusManager.current
    val uriHandler = LocalUriHandler.current
    val scrollState = rememberScrollState()
    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(R.drawable.background),
        contentDescription = null,
        contentScale = ContentScale.FillBounds
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = { focusManager.clearFocus() },
                )
            }
            .verticalScroll(state = scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .height(225.dp)
                .padding(top = 30.dp),
            painter = painterResource(R.drawable.dog_background),
            contentDescription = null
        )
        Text(
            modifier = Modifier.padding(top = 20.dp, bottom = 30.dp),
            text = stringResource(id = R.string.login_screen_authorisation),
            style = PetlandTheme.typography.bigTitle
        )
        DefaultTextField(
            modifier = Modifier.padding(horizontal = 30.dp),
            value = state.login.value,
            onValueChange = onLoginChange,
            placeholder = stringResource(id = R.string.login_screen_email_or_username),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            ),
            isError = state.login.isNotAccepted,
            error = when (state.login.acceptance) {
                Acceptance.INCORRECT_EMAIL -> stringResource(id = R.string.login_screen_email_error)
                else -> null
            }
        )
        PasswordTextField(
            modifier = Modifier.padding(horizontal = 30.dp, vertical = 10.dp),
            value = state.password.value,
            onValueChange = onPasswordChange,
            onVisibilityChange = onPasswordVisibilityChange,
            passwordVisible = state.isPasswordVisible,
            placeholder = stringResource(R.string.login_screen_password_placeholder),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus() }
            ),
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
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp, vertical = 5.dp)
                .clickable { uriHandler.openUri(RESTORE_PASSWORD) },
            text = stringResource(id = R.string.login_screen_forgot_password),
            style = PetlandTheme.typography.underlinedText,
            textAlign = TextAlign.End,
            color = PetlandTheme.colors.textLight
        )
        TextWithCheckBox(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.login_screen_stay_logged),
            checked = state.isCheckBoxChecked,
            onCheckedChange = onCheckedChange,
        )
        if (state.isErrorAppeared) {
            Text(
                modifier = Modifier.padding(vertical = 5.dp),
                text = stringResource(id = R.string.login_screen_authorization_error),
                style = PetlandTheme.typography.outlinedButtonTitle,
                color = PetlandTheme.colors.error
            )
        }
        DefaultButton(
            modifier = Modifier
                .padding(vertical = 10.dp)
                .width(100.dp),
            text = stringResource(id = R.string.login_screen_sign_in),
            enabled = state.isLogInButtonEnabled,
            onClick = onLogin
        )
        Text(
            modifier = Modifier
                .padding(start = 30.dp, end = 30.dp, top = 10.dp, bottom = 2.dp),
            text = stringResource(id = R.string.login_screen_have_not_signed_up),
            style = PetlandTheme.typography.smallText,
            color = PetlandTheme.colors.textLight,
        )
        Text(
            modifier = Modifier
                .padding(horizontal = 30.dp, vertical = 2.dp)
                .clickable { onSignUp() },
            text = stringResource(id = R.string.login_screen_sign_up),
            style = PetlandTheme.typography.underlinedText,
            color = PetlandTheme.colors.textLight,
        )
        if (state.isLoading) {
            Loader()
        }
    }
}
