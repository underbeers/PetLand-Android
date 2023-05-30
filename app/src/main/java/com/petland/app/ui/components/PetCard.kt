package com.petland.app.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.petland.app.data.model.local.Pet
import com.petland.app.ui.theme.PetlandTheme

@OptIn(ExperimentalFoundationApi::class)
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
                model = pet.link,
                contentDescription = null,
                contentScale = ContentScale.FillHeight
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = pet.name,
                style = PetlandTheme.typography.bigTitle
            )
        }
    }
}