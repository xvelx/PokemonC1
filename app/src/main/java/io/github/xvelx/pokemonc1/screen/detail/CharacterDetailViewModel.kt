package io.github.xvelx.pokemonc1.screen.detail

import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.xvelx.pokemonc1.domain.model.PokemonCharacterDetail
import io.github.xvelx.pokemonc1.domain.repository.PokemonRepository
import io.github.xvelx.pokemonc1.screen.common.BaseIntent
import io.github.xvelx.pokemonc1.screen.common.BaseSideEffect
import io.github.xvelx.pokemonc1.screen.common.BaseUiState
import io.github.xvelx.pokemonc1.screen.common.BaseViewModel
import javax.inject.Inject

sealed interface CharacterDetailIntent : BaseIntent {
    data class Load(val id: Int) : CharacterDetailIntent
}

sealed interface CharacterDetailSideEffect : BaseSideEffect

sealed interface CharacterDetailUiState : BaseUiState {
    data object Loading : CharacterDetailUiState
    data class Present(val detailModel: PokemonCharacterDetail) : CharacterDetailUiState
}

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
) : BaseViewModel<CharacterDetailIntent, CharacterDetailSideEffect, CharacterDetailUiState>(
    CharacterDetailUiState.Loading
) {

    override fun sendIntent(intent: CharacterDetailIntent) {
        when (intent) {
            is CharacterDetailIntent.Load -> launch {
                pokemonRepository.fetchCharacterDetail(intent.id)
                    .onSuccess { detail ->
                        updateUiState { CharacterDetailUiState.Present(detail) }
                    }
            }
        }
    }
}
