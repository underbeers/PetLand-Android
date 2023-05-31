package com.petland.app.features.pet.info

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.PostAdd
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.petland.app.R
import com.petland.app.ui.components.DefaultButton
import com.petland.app.ui.theme.PetlandTheme

@Composable
fun PetAbsenceScreen(
    onPetAddClick: () -> Unit,
    onBulletinBoardClick: () -> Unit
) {
    Text(
        modifier = Modifier.padding(top = 12.dp),
        text = stringResource(id = R.string.pet_absence_screen_header),
        style = PetlandTheme.typography.defaultButtonText,
    )
    Text(
        modifier = Modifier.padding(vertical = 8.dp).fillMaxWidth(),
        text = stringResource(id = R.string.pet_absence_screen_description),
        style = PetlandTheme.typography.smallText,
        color = PetlandTheme.colors.textLight,
        textAlign = TextAlign.Justify
    )
    DefaultButton(
        text = stringResource(id = R.string.pet_absence_screen_add_pet),
        enabled = true,
        icon = Icons.Filled.Add,
        onClick = onPetAddClick,
    )
    Text(
        modifier = Modifier.padding(top = 16.dp, bottom = 8.dp).fillMaxWidth(),
        text = stringResource(id = R.string.pet_absence_screen_description_for_advert),
        style = PetlandTheme.typography.smallText,
        color = PetlandTheme.colors.textLight,
        textAlign = TextAlign.Justify
    )
    DefaultButton(
        text = stringResource(id = R.string.pet_absence_screen_button_to_advert),
        enabled = true,
        icon = Icons.Filled.PostAdd,
        color = PetlandTheme.colors.secondary,
        onClick = onBulletinBoardClick
    )
}

