package com.petland.app.features.favorites

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.petland.app.ui.components.ToggleButtonCustom
import com.petland.app.ui.theme.PetlandTheme

@Composable
fun FavoritesScreen() {
    FavoritesContent()
}

@Composable
fun FavoritesContent() {
    ToggleMenu(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        services = listOf("test", "test1", "test2"),
        selectedService = "test1",
        onServiceSelected = {})
}


@Composable
private fun ToggleMenu(
    services: List<String>,
    selectedService: String,
    modifier: Modifier = Modifier,
    onServiceSelected: (String) -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .border(color = PetlandTheme.colors.secondary, width = 1.dp)
    ) {
        services.forEach { service ->
            ToggleButtonCustom(
                modifier = Modifier.weight(1f),
                service = service,
                isSelected = service == selectedService,
                onItemSelect = onServiceSelected,
            )
        }
    }
}