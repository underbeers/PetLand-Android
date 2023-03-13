package com.petland.app.features.sign_up

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.petland.app.R
import com.petland.app.features.sign_up.content.SignUpFirstStepContent
import com.petland.app.features.sign_up.content.SignUpSecondStepContent
import com.petland.app.features.sign_up.content.SignUpThirdStepContent
import com.petland.app.ui.components.DefaultButton
import com.petland.app.ui.theme.PetlandTheme
import com.petland.app.util.Step


@Composable
fun SignUpScreen(
    navController: NavController,
) {
    val signUpViewModel = hiltViewModel<SignUpViewModel>()
    val state by signUpViewModel.state.collectAsState()
    SignUpContent(
        state = state,
        onFirstNameChange = signUpViewModel::onFirstNameChange,
        onLastNameChange = signUpViewModel::onLastNameChange,
        onEmailChange = signUpViewModel::onEmailChange,
        onPasswordChange = signUpViewModel::onPasswordChange,
        onRepeatedPasswordChange = signUpViewModel::onRepeatPasswordChange,
        onNavigateToNextStep = signUpViewModel::onNavigateToNextStep,
        onNavigateToLogIn = signUpViewModel::onNavigateToLogIn,
        onSendCode = signUpViewModel::onEmailVerify,
        onSendCodeChange = signUpViewModel::onSendCodeChange,
        onPasswordVisibilityChange = signUpViewModel::onPasswordVisibilityChange,
        onRepeatedPasswordVisibilityChange = signUpViewModel::onRepeatedPasswordVisibilityChange,
        onCheckedChange = signUpViewModel::onCheckedChange,
        onSignUp = signUpViewModel::onSignUp,
    )
    LaunchedEffect(key1 = signUpViewModel.effect) {
        signUpViewModel.effect.collect { effect ->
            when (effect) {
                is SignUpEffect.NavigateToLogIn -> navController.navigateUp()
            }
        }
    }
}

@Composable
fun SignUpContent(
    state: SignUpState,
    onFirstNameChange: (String) -> Unit,
    onLastNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onRepeatedPasswordChange: (String) -> Unit,
    onNavigateToNextStep: () -> Unit,
    onNavigateToLogIn: () -> Unit,
    onSendCodeChange: (String) -> Unit,
    onSendCode: () -> Unit,
    onPasswordVisibilityChange: () -> Unit,
    onRepeatedPasswordVisibilityChange: () -> Unit,
    onCheckedChange: (Boolean) -> Unit,
    onSignUp: () -> Unit,
) {
    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(R.drawable.background),
        contentDescription = null,
        contentScale = ContentScale.FillBounds
    )
    Column(
        modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .height(225.dp)
                .padding(top = 30.dp),
            painter = painterResource(R.drawable.dog_background),
            contentDescription = null,
        )
        Text(
            modifier = Modifier.padding(top = 10.dp, bottom = 15.dp),
            text = stringResource(id = R.string.registration_screen_title),
            style = PetlandTheme.typography.bigTitle
        )
        Box(Modifier.weight(5f)) {
            when (state.currentStep) {
                Step.FIRST.ordinal -> SignUpFirstStepContent(
                    state = state,
                    onFirstNameChange = onFirstNameChange,
                    onLastNameChange = onLastNameChange,
                )
                Step.SECOND.ordinal -> SignUpSecondStepContent(
                    state = state,
                    onEmailChange = onEmailChange,
                    onSendCode = onSendCode,
                    onSendCodeChange = onSendCodeChange
                )
                Step.THIRD.ordinal -> SignUpThirdStepContent(
                    state = state,
                    onPasswordChange = onPasswordChange,
                    onRepeatedPasswordChange = onRepeatedPasswordChange,
                    onPasswordVisibilityChange = onPasswordVisibilityChange,
                    onRepeatedPasswordVisibilityChange = onRepeatedPasswordVisibilityChange,
                    onCheckedChange = onCheckedChange,
                )
            }
        }
        if (state.isSignedUpSuccessfully.not() && state.isErrorAppeared) {
            Text(
                modifier = Modifier.padding(vertical = 5.dp, horizontal = 30.dp),
                text = stringResource(id = R.string.registration_screen_error),
                style = PetlandTheme.typography.outlinedButtonTitle,
                color = PetlandTheme.colors.error
            )
        }
        DefaultButton(
            modifier = Modifier
                .padding(vertical = 5.dp)
                .width(200.dp),
            text = when (state.currentStep) {
                Step.THIRD.ordinal -> stringResource(id = R.string.registration_screen_create_account)
                else -> stringResource(id = R.string.registration_screen_next_step)
            },
            enabled = when (state.currentStep) {
                Step.FIRST.ordinal -> state.isAllowedMoveToSecondStep
                Step.SECOND.ordinal -> state.isAllowedMoveToThirdStep
                else -> state.isAllowedToFinishSignUp
            },
            onClick = {
                when (state.currentStep) {
                    Step.THIRD.ordinal -> onSignUp()
                    else -> onNavigateToNextStep()
                }
            }
        )
        Text(
            modifier = Modifier.padding(start = 30.dp, end = 30.dp, top = 10.dp, bottom = 2.dp),
            text = stringResource(id = R.string.registration_screen_account_already_exists),
            style = PetlandTheme.typography.smallText,
            color = PetlandTheme.colors.textLight,
        )
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = 30.dp, end = 30.dp, bottom = 2.dp)
                .clickable { onNavigateToLogIn() },
            text = stringResource(id = R.string.registration_screen_login),
            style = PetlandTheme.typography.underlinedText,
            color = PetlandTheme.colors.textLight,
        )
    }
}