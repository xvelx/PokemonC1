package io.github.xvelx.pokemonc1.domain.repository

import io.github.xvelx.pokemonc1.domain.model.PokemonCharacter
import io.github.xvelx.pokemonc1.domain.model.PokemonCharacterDetail

interface PokemonRepository {

    suspend fun fetchCharacters(offset: Int, pageSize: Int): Result<List<PokemonCharacter>>

    suspend fun fetchCharacterDetail(characterId: Int): Result<PokemonCharacterDetail>
}
