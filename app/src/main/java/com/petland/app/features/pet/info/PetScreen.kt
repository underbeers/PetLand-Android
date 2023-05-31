package com.petland.app.features.pet.info

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.petland.app.R
import com.petland.app.ext.popUpDestinationAndNavigate
import com.petland.app.navigation.Screen
import com.petland.app.ui.theme.PetlandTheme
import com.petland.app.util.PetScreenType

@Composable
fun PetScreen(navController: NavController, userName: String, avatarLink: String) {
    val petViewModel = hiltViewModel<PetViewModel>()
    val state by petViewModel.state.collectAsState()
    petViewModel.setUserInfo(name = userName, avatarLink = avatarLink)
    PetContent(
        state = state,
        onBulletinBoardClick = petViewModel::onBulletinBoardClick,
        onPetAddClick = petViewModel::onPetAddClick,
    )
    LaunchedEffect(key1 = petViewModel.effect) {
        petViewModel.effect.collect { effect ->
            when (effect) {
                PetEffect.NavigateToBulletinBoard -> navController.popUpDestinationAndNavigate(
                    Screen.BulletinBoard.route
                )

                PetEffect.NavigateToAddPet -> {
                    navController.navigate(Screen.PetAdd.route)
                }

                PetEffect.NavigateToEditPet -> {
                    navController.navigate(Screen.PetEdit.route)
                }
            }
        }
    }
}

@Composable
fun PetContent(
    state: PetState,
    onPetAddClick: () -> Unit,
    onBulletinBoardClick: () -> Unit,
) {
    val verticalScrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(state = verticalScrollState),
    ) {
        Row(
            modifier = Modifier.padding(top = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(100.dp),
                contentScale = ContentScale.Crop,
                model = state.avatar,
                contentDescription = null
            )
            Text(
                text = stringResource(
                    id = R.string.pet_screen_name,
                    state.userName
                ),
                style = PetlandTheme.typography.defaultButtonText
            )
        }
        Divider(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp))
        when (state.petScreenType) {
            PetScreenType.PET_ABSENCE -> PetAbsenceScreen(
                onBulletinBoardClick = onBulletinBoardClick,
                onPetAddClick = onPetAddClick
            )

            PetScreenType.PET_AVAILABLE -> PetAvailableContent(state)
        }
    }
}