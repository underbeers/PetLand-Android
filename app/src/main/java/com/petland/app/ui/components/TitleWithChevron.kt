package com.petland.app.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.petland.app.R
import com.petland.app.ui.theme.PetlandTheme
import com.petland.app.ui.theme.StarDisabledColor
import com.petland.app.ui.theme.StarEnabledColor

@Composable
fun TitleWithChevron(
    modifier: Modifier,
    title: String,
    description: String = "",
    isChevronEnabled: Boolean,
    isRatingVisible: Boolean = false,
    rating: Double = 0.0,
    reviews: Int = 0,
    onTitleClick: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    Row(
        modifier = modifier
            .height(45.dp)
            .fillMaxWidth()
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) { onTitleClick() },
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = PetlandTheme.typography.bigTitle,
                color = PetlandTheme.colors.text
            )
            if (isRatingVisible) {
                RatingBar(rating, reviews)
            } else {
                Text(
                    text = description,
                    style = PetlandTheme.typography.smallText,
                    color = PetlandTheme.colors.textLight,
                )
            }
        }
        if (isChevronEnabled) Image(
            painter = painterResource(R.drawable.ic_chevron_right),
            contentDescription = null
        )
    }
}

@Composable
fun RatingBar(
    rating: Double,
    reviewsAmount: Int,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        repeat(5) { index ->
            Icon(
                painter = painterResource(R.drawable.ic_star),
                contentDescription = null,
                tint = if (index < rating) StarEnabledColor else StarDisabledColor,
                modifier = Modifier.size(18.dp)
            )
        }
        Text(
            text = when (reviewsAmount) {
                0 -> ""
                1 -> stringResource(
                    R.string.profile_screen_rating_review,
                    reviewsAmount
                )

                else -> stringResource(
                    id = R.string.profile_screen_rating_reviews, reviewsAmount
                )
            },
            style = PetlandTheme.typography.underlinedText,
            color = PetlandTheme.colors.textLight,
        )
    }
}