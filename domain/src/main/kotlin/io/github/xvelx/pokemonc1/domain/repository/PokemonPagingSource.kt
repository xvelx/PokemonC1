package io.github.xvelx.pokemonc1.domain.repository

import androidx.paging.PagingSource
import io.github.xvelx.pokemonc1.domain.model.PokemonCharacter

abstract class PokemonPagingSource : PagingSource<Int, PokemonCharacter>()