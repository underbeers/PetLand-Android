package com.petland.app.features.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.petland.app.R
import com.petland.app.ui.components.DefaultButton
import com.petland.app.ui.theme.PetlandTheme

@Composable
fun UnauthorizedUserContent(
    onAuthorize: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 120.dp, bottom = 20.dp),
            text = stringResource(id = R.string.profile_screen_unauthorized),
            style = PetlandTheme.typography.suggestionTitle,
            textAlign = TextAlign.Center
        )
        DefaultButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.profile_screen_authorize_button),
            enabled = true,
            onClick = onAuthorize
        )
    }
}