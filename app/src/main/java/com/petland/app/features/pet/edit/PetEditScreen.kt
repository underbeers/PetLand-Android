package com.petland.app.features.pet.edit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.petland.app.R
import com.petland.app.data.model.remote.response.Breed
import com.petland.app.data.model.remote.response.PetType
import com.petland.app.ui.components.DefaultTextField
import com.petland.app.ui.components.DropDownMenuBreed
import com.petland.app.ui.components.DropDownMenuGender
import com.petland.app.ui.components.DropDownMenuPetType


@Composable
fun PetEditScreen() {
    val petEditViewModel = hiltViewModel<PetEditViewModel>()
    val state by petEditViewModel.state.collectAsState()
    PetEditContent(
        state = state,
        onNameChange = petEditViewModel::onNameChange,
        onTypeSelect = petEditViewModel::onTypeSelect,
        onBreedSelected = petEditViewModel::onBreedSelected,
        onGenderSelected = petEditViewModel::onGenderSelected
    )
}

@Composable
fun PetEditContent(
    state: PetEditState,
    onNameChange: (String) -> Unit,
    onTypeSelect: (PetType) -> Unit,
    onBreedSelected: (Breed) -> Unit,
    onGenderSelected: (String) -> Unit,
) {
    val verticalScrollState = rememberScrollState()
    val focusManager = LocalFocusManager.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(state = verticalScrollState),
    ) {
        DefaultTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            value = state.petName,
            onValueChange = onNameChange,
            placeholder = stringResource(id = R.string.pet_screen_edit_alias_description),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) }),
            error = null,
            label = stringResource(id = R.string.pet_screen_edit_alias),
            isLabelVisible = true,
            isRequired = true,
        )
        DropDownMenuPetType(
            modifier = Modifier.padding(bottom = 12.dp),
            items = state.typeList,
            onSelectedOptionChange = onTypeSelect,
            placeholder = stringResource(id = R.string.pet_screen_edit_type_description),
            selectedOption = state.type,
            label = stringResource(id = R.string.pet_screen_edit_type)
        )
        DropDownMenuBreed(
            modifier = Modifier.padding(bottom = 12.dp),
            items = state.breeds,
            selectedOption = state.breed,
            placeholder = stringResource(id = R.string.pet_screen_breed_description),
            label = stringResource(id = R.string.pet_screen_breed_title),
            onSelectedOptionChange = onBreedSelected
        )
        DropDownMenuGender(
            items = state.genders,
            selectedOption = state.gender,
            placeholder = stringResource(id = R.string.pet_screen_gender_description),
            label = stringResource(id = R.string.pet_screen_gender_title),
            onSelectedOptionChange = onGenderSelected,
        )
    }
}