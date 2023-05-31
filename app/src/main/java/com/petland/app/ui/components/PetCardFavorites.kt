package com.petland.app.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun PetCardFavorites(
    link: String,
    name: String,
    price: String,
    address: String,
    date: String,
    modifier: Modifier = Modifier
) {
    Row {
        AsyncImage(
            modifier = Modifier.size(100.dp),
            model = link,
            contentDescription = null,
            contentScale = ContentScale.FillHeight
        )
        Column {
            Text(text = name, )
        }
    }
}