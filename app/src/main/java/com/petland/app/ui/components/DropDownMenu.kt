package com.petland.app.ui.components

import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.petland.app.data.model.remote.response.Breed
import com.petland.app.data.model.remote.response.PetType
import com.petland.app.ui.theme.PetlandTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DropDownMenuPetType(
    items: List<PetType>,
    selectedOption: PetType,
    placeholder: String,
    label: String,
    modifier: Modifier = Modifier,
    onSelectedOptionChange: (PetType) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        DefaultTextField(
            value = selectedOption.petType,
            onValueChange = { },
            placeholder = placeholder,
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            isRequired = true,
            isLabelVisible = true,
            label = label,
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            items.forEach { selectionOption ->
                DropdownMenuItem(
                    onClick = {
                        expanded = expanded.not()
                        onSelectedOptionChange(selectionOption)
                    }
                ) {
                    Text(text = selectionOption.petType, style = PetlandTheme.typography.text)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DropDownMenuBreed(
    items: List<Breed>,
    selectedOption: Breed,
    placeholder: String,
    label: String,
    modifier: Modifier = Modifier,
    onSelectedOptionChange: (Breed) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        DefaultTextField(
            value = selectedOption.breedName,
            onValueChange = { },
            placeholder = placeholder,
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            isRequired = true,
            isLabelVisible = true,
            label = label,
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            items.forEach { selectionOption ->
                DropdownMenuItem(
                    onClick = {
                        expanded = expanded.not()
                        onSelectedOptionChange(selectionOption)
                    }
                ) {
                    Text(text = selectionOption.breedName, style = PetlandTheme.typography.text)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DropDownMenuGender(
    items: List<String>,
    selectedOption: String,
    placeholder: String,
    label: String,
    modifier: Modifier = Modifier,
    onSelectedOptionChange: (String) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        DefaultTextField(
            value = selectedOption,
            onValueChange = { },
            placeholder = placeholder,
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            isRequired = true,
            isLabelVisible = true,
            label = label,
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            items.forEach { selectionOption ->
                DropdownMenuItem(
                    onClick = {
                        expanded = expanded.not()
                        onSelectedOptionChange(selectionOption)
                    }
                ) {
                    Text(text = selectionOption, style = PetlandTheme.typography.text)
                }
            }
        }
    }
}