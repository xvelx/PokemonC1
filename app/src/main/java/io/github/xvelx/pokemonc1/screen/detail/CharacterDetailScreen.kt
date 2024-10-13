package io.github.xvelx.pokemonc1.screen.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import io.github.xvelx.pokemonc1.R
import io.github.xvelx.pokemonc1.composable.AppProgressIndicator
import io.github.xvelx.pokemonc1.composable.VerticalSpacer
import io.github.xvelx.pokemonc1.domain.model.PokemonCharacterDetail
import java.util.Locale

@Composable
fun CharacterDetailScreen(
    id: Int,
    viewModel: CharacterDetailViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsState(CharacterDetailUiState.Loading)

    when (uiState) {
        is CharacterDetailUiState.Loading -> AppProgressIndicator()

        is CharacterDetailUiState.Present -> CharacterDetail(
            (uiState as CharacterDetailUiState.Present).detailModel
        )
    }

    LaunchedEffect(Unit) {
        viewModel.sendIntent(CharacterDetailIntent.Load(id))
    }
}

@Composable
private fun CharacterDetail(
    detailModel: PokemonCharacterDetail
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(detailModel.imageUrl)
                .build(),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray.copy(alpha = 0.5f))
                .defaultMinSize(minHeight = 250.dp)
                .align(Alignment.CenterHorizontally),
            contentDescription = null
        )

        VerticalSpacer()

        Text(
            detailModel.name,
            modifier = Modifier.fillMaxWidth(),
            style = TextStyle(
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        )

        VerticalSpacer()

        Text(
            String.format(
                Locale.getDefault(),
                stringResource(R.string.size_content),
                detailModel.height,
                detailModel.weight
            ),
            modifier = Modifier.fillMaxWidth(),
            style = TextStyle(
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        )

        SectionMarker()

        HeaderLabel(stringResource(R.string.header_ability))
        VerticalSpacer(8.dp)
        detailModel.abilities.forEach {
            ValueLabel(it)
            VerticalSpacer(8.dp)
        }


        SectionMarker()

        HeaderLabel(stringResource(R.string.header_stats))
        VerticalSpacer(8.dp)
        detailModel.states.forEach {
            ValueLabel("${it.key}: ${it.value}")
            VerticalSpacer(8.dp)
        }

        SectionMarker()
    }
}

@Composable
private fun SectionMarker() {
    VerticalSpacer()
    HorizontalDivider()
    VerticalSpacer()
}

@Composable
private fun HeaderLabel(text: String) {
    Text(
        text,
        style = TextStyle(
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    )
}

@Composable
private fun ValueLabel(text: String) {
    Text(
        text,
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Color.Black.copy(alpha = 0.7f))
            .padding(8.dp),
        style = TextStyle(
            color = Color.White,
            fontSize = 12.sp
        )
    )
}

