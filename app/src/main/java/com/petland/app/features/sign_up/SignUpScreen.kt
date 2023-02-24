package com.petland.app.features.sign_up

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.petland.app.ui.components.CustomDialog
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
        onNavigateToFirstStep = signUpViewModel::onNavigateToPreviousStep,
        onNavigateToSecondStep = signUpViewModel::onNavigateToNextStep,
        onNavigateToLogIn = signUpViewModel::onNavigateToLogIn,
        onSendCode = signUpViewModel::onEmailVerify,
        onSendCodeChange = signUpViewModel::onSendCodeChange,
        onPasswordVisibilityChange = signUpViewModel::onPasswordVisibilityChange,
        onRepeatedPasswordVisibilityChange = signUpViewModel::onRepeatedPasswordVisibilityChange,
        onCheckedChange = signUpViewModel::onCheckedChange,
        onSignUp = signUpViewModel::onSignUp,
        onDialogExit = signUpViewModel::onDialogExit,
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
    onNavigateToFirstStep: () -> Unit,
    onNavigateToSecondStep: () -> Unit,
    onNavigateToLogIn: () -> Unit,
    onSendCodeChange: (String) -> Unit,
    onSendCode: () -> Unit,
    onPasswordVisibilityChange: () -> Unit,
    onRepeatedPasswordVisibilityChange: () -> Unit,
    onCheckedChange: (Boolean) -> Unit,
    onSignUp: () -> Unit,
    onDialogExit: () -> Unit,
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
                .fillMaxWidth()
                .height(180.dp),
            painter = painterResource(R.drawable.dog_background_2),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
        Text(
            modifier = Modifier.padding(top = 15.dp, bottom = 10.dp),
            text = stringResource(id = R.string.registration_screen_title),
            style = PetlandTheme.typography.bigTitle
        )
        if (state.isSignedUpSuccessfully && !state.isErrorAppeared) {
            CustomDialog(
                title = stringResource(id = R.string.login_screen_success_title),
                message = stringResource(id = R.string.login_screen_success_message),
                isDialogOpen = state.isDialogDisplayed,
                onDialogExit = onDialogExit
            )
        }
        when (state.currentStep) {
            Step.FIRST.ordinal -> SignUpFirstStepContent(
                state = state,
                onFirstNameChange = onFirstNameChange,
                onLastNameChange = onLastNameChange,
                onEmailChange = onEmailChange,
                onNavigateToSecondStep = onNavigateToSecondStep,
                onNavigateToLogIn = onNavigateToLogIn,
                onSendCode = onSendCode,
                onSendCodeChange = onSendCodeChange
            )
            Step.SECOND.ordinal -> SignUpSecondStepContent(
                state = state,
                onPasswordChange = onPasswordChange,
                onRepeatedPasswordChange = onRepeatedPasswordChange,
                onPasswordVisibilityChange = onPasswordVisibilityChange,
                onRepeatedPasswordVisibilityChange = onRepeatedPasswordVisibilityChange,
                onNavigateToFirstStep = onNavigateToFirstStep,
                onCheckedChange =  onCheckedChange,
                onSignUp = onSignUp
            )
        }
    }
}