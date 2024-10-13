package io.github.xvelx.pokemonc1.data.repository

import io.github.xvelx.pokemonc1.data.api.PokemonApiClient
import io.github.xvelx.pokemonc1.data.mapper.PokemonCharacterDetailMapper
import io.github.xvelx.pokemonc1.data.mapper.PokemonCharacterMapper
import io.github.xvelx.pokemonc1.domain.model.PokemonCharacter
import io.github.xvelx.pokemonc1.domain.model.PokemonCharacterDetail
import io.github.xvelx.pokemonc1.domain.repository.PokemonRepository
import io.github.xvelx.pokemonc1.network.AppCoroutineDispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PokemonRepositoryImpl @Inject internal constructor(
    private val pokemonApiClient: PokemonApiClient,
    private val pokemonCharacterMapper: PokemonCharacterMapper,
    private val pokemonCharacterDetailMapper: PokemonCharacterDetailMapper,
    private val appCoroutineDispatchers: AppCoroutineDispatchers
) : PokemonRepository {

    override suspend fun fetchCharacters(
        offset: Int,
        pageSize: Int
    ): Result<List<PokemonCharacter>> = withContext(appCoroutineDispatchers.io) {
        runCatching {
            val characterReferences = pokemonApiClient.fetchCharacters(offset, pageSize)
            pokemonCharacterMapper.map(characterReferences.results)
        }
    }

    override suspend fun fetchCharacterDetail(
        characterId: Int
    ): Result<PokemonCharacterDetail> = withContext(appCoroutineDispatchers.io) {
        runCatching {
            pokemonCharacterDetailMapper.map(
                pokemonApiClient.fetchCharacterDetail(characterId)
            )
        }
    }
}
