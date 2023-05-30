package com.petland.app.features.profile

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.petland.app.R
import com.petland.app.ui.components.PetCard
import com.petland.app.ui.components.TitleWithChevron
import com.petland.app.ui.theme.PetlandTheme
import com.petland.app.util.getCurrentYear
import com.petland.app.util.getMonthName

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UserProfileContent(
    state: ProfileState,
    onPetsClick: () -> Unit,
    onRatingClick: () -> Unit,
    onAdvertClick: () -> Unit,
) {
    val pagerState = rememberPagerState()
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
                    id = R.string.profile_screen_name,
                    state.name,
                    state.surname,
                ),
                style = PetlandTheme.typography.defaultButtonText
            )
        }
        Text(
            modifier = Modifier.padding(top = 8.dp, bottom = 6.dp),
            text = stringResource(
                id = R.string.profile_screen_sign_up_date,
                getMonthName(),
                getCurrentYear()
            ),
            style = PetlandTheme.typography.text,
            color = PetlandTheme.colors.textLight
        )
        Divider(modifier = Modifier.fillMaxWidth())
        Text(
            modifier = Modifier.padding(top = 24.dp),
            text = stringResource(id = R.string.profile_screen_description),
            style = PetlandTheme.typography.bigTitle,
            color = PetlandTheme.colors.header4
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            text = state.description,
            style = PetlandTheme.typography.text,
            color = PetlandTheme.colors.text,
            textAlign = TextAlign.Justify
        )
        TitleWithChevron(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            isChevronEnabled = state.isOtherProfile.not(),
            isRatingVisible = false,
            title = stringResource(id = R.string.profile_screen_pets_title),
            description =
            if (state.pets.size > 1 || state.pets.isEmpty()) stringResource(
                R.string.profile_screen_pets_description,
                state.pets.size
            ) else stringResource(
                R.string.profile_screen_pets_description_singular,
                state.pets.size
            ),
            onTitleClick = onPetsClick
        )
        if (state.isOtherProfile) {
            HorizontalPager(
                modifier = Modifier.padding(bottom = 10.dp),
                pageSize = PageSize.Fixed(160.dp),
                pageSpacing = 10.dp ,
                pageCount = state.pets.size,
                state = pagerState,
            ) { page ->
                PetCard(pet = state.pets[page])
            }
        }
        TitleWithChevron(
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(id = R.string.profile_screen_rating),
            isChevronEnabled = state.isOtherProfile.not(),
            isRatingVisible = true,
            rating = state.rating,
            onTitleClick = onRatingClick,
            reviews = state.reviewsAmount
        )
        TitleWithChevron(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            title = stringResource(id = R.string.bulletin_board_screen_title),
            isChevronEnabled = state.isOtherProfile.not(),
            description = when (state.advertAmount) {
                0 -> stringResource(
                    id = R.string.profile_screen_advert_description_zero_adverts,
                    state.advertAmount
                )

                1 -> stringResource(
                    id = R.string.profile_screen_advert_description_singular,
                    state.advertAmount
                )

                else -> stringResource(
                    id = R.string.profile_screen_advert_description,
                    state.advertAmount
                )
            },
            onTitleClick = onAdvertClick,
        )
    }
}