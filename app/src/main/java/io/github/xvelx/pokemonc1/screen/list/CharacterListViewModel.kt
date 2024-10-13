package io.github.xvelx.pokemonc1.screen.list

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.xvelx.pokemonc1.domain.repository.PokemonPagingSource
import io.github.xvelx.pokemonc1.screen.common.BaseIntent
import io.github.xvelx.pokemonc1.screen.common.BaseSideEffect
import io.github.xvelx.pokemonc1.screen.common.BaseUiState
import io.github.xvelx.pokemonc1.screen.common.BaseViewModel
import javax.inject.Inject

sealed interface CharacterListIntent : BaseIntent {
    data class Select(val id: Int) : CharacterListIntent
}

sealed interface CharacterListSideEffect : BaseSideEffect {
    data class NavigateToDetail(val characterId: Int) : CharacterListSideEffect
}

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val pokemonPagingSource: PokemonPagingSource
) : BaseViewModel<CharacterListIntent, CharacterListSideEffect, BaseUiState>(
    BaseUiState.Loading
) {

    val pokemonCharacterPager by lazy {
        Pager(PagingConfig(pageSize = PAGE_SIZE)) { pokemonPagingSource }
            .flow
            .cachedIn(viewModelScope)
    }

    override fun sendIntent(intent: CharacterListIntent) {
        when (intent) {
            is CharacterListIntent.Select -> postSideEffect(
                CharacterListSideEffect.NavigateToDetail(intent.id)
            )
        }
    }

    private companion object {
        const val PAGE_SIZE = 20
    }
}
