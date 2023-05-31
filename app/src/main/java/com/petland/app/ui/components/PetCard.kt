package com.petland.app.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.petland.app.data.model.remote.response.Pet
import com.petland.app.ui.theme.PetlandTheme
import com.petland.app.util.getYears

@Composable
fun PetCard(pet: Pet) {
    Card(
        modifier = Modifier.size(size = 160.dp),
        shape = RoundedCornerShape(6.dp),
        elevation = 5.dp
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                modifier = Modifier
                    .width(160.dp)
                    .height(120.dp),
                model = pet.photo,
                contentDescription = null,
                contentScale = ContentScale.FillHeight
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = pet.petName,
                style = PetlandTheme.typography.bigTitle
            )
        }
    }
}

@Composable
fun PetCardWithChips(
    modifier: Modifier = Modifier,
    pet: Pet
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card(
            modifier = Modifier
                .width(150.dp)
                .height(220.dp),
            shape = RoundedCornerShape(4.dp),
            elevation = 5.dp
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(4.dp)),
                model = pet.photo,
                contentDescription = null,
                contentScale = ContentScale.FillHeight,
            )
        }
        Column(modifier = Modifier.height(220.dp)) {
            Text(
                modifier = Modifier.padding(bottom = 24.dp),
                text = pet.petName,
                style = PetlandTheme.typography.header5,
                color = PetlandTheme.colors.text,
            )
            TextWithChip(
                modifier = Modifier,
                text = pet.petType
            )
            TextWithChip(
                modifier = Modifier.padding(vertical = 2.dp),
                text = pet.breed
            )
            TextWithChip(
                modifier = Modifier,
                text = pet.gender
            )
            TextWithChip(
                modifier = Modifier.padding(vertical = 2.dp,),
                text = getYears(pet.birthDate).toString()
            )
        }
    }
}