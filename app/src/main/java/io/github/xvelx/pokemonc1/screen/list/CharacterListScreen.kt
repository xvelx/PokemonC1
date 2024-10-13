package io.github.xvelx.pokemonc1.screen.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import io.github.xvelx.pokemonc1.composable.AppProgressIndicator
import io.github.xvelx.pokemonc1.composable.LocalNavController
import io.github.xvelx.pokemonc1.composable.Screens
import io.github.xvelx.pokemonc1.composable.VerticalSpacer
import io.github.xvelx.pokemonc1.domain.model.PokemonCharacter
import kotlinx.coroutines.flow.collectLatest

@Composable
fun CharacterListScreen(
    listViewModel: CharacterListViewModel = hiltViewModel()
) {
    val currentNavController = LocalNavController.current
    val lazyPagingItems = listViewModel.pokemonCharacterPager.collectAsLazyPagingItems()

    CharacterList(lazyPagingItems, listViewModel::sendIntent)

    if (
        lazyPagingItems.loadState.refresh == LoadState.Loading ||
        lazyPagingItems.loadState.append == LoadState.Loading
    ) {
        AppProgressIndicator()
    }

    LaunchedEffect(Unit) {
        listViewModel.sideEffect.collectLatest { effect ->
            when (effect) {
                is CharacterListSideEffect.NavigateToDetail ->
                    currentNavController?.navigate(Screens.Detail(effect.characterId))
            }
        }
    }
}

@Composable
private fun CharacterList(
    pageItems: LazyPagingItems<PokemonCharacter>,
    sendIntent: (intent: CharacterListIntent) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(pageItems.itemCount) {
            pageItems[it]?.let { itemData ->
                CharacterItem(itemData, sendIntent)
            }
        }
    }
}

@Composable
private fun CharacterItem(
    itemData: PokemonCharacter,
    sendIntent: (intent: CharacterListIntent) -> Unit
) {
    Column(
        modifier = Modifier
            .clickable { sendIntent(CharacterListIntent.Select(itemData.id)) }
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(itemData.imageUrl)
                .build(),
            modifier = Modifier
                .defaultMinSize(minHeight = 150.dp, minWidth = 150.dp)
                .clip(CircleShape)
                .background(Color.LightGray.copy(alpha = 0.7f))
                .padding(16.dp),
            contentDescription = null
        )

        VerticalSpacer(1.dp)

        Text(
            itemData.name,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(Color.Magenta.copy(alpha = 0.7f))
                .padding(4.dp),
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )
        )
    }
}
