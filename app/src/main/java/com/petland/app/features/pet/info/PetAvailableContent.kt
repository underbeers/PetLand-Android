package com.petland.app.features.pet.info

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.petland.app.ui.components.PetCardWithChips

@Composable
fun PetAvailableContent(
    state: PetState
) {
    repeat(state.pets.size) { index ->
        PetCardWithChips(
            modifier = Modifier.padding(top = 20.dp, bottom = 5.dp),
            pet = state.pets[index]
        )
    }
}

