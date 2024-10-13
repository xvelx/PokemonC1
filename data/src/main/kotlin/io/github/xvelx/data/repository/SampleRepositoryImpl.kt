package io.github.xvelx.data.repository

import io.github.xvelx.data.api.PokemonApiClient
import io.github.xvelx.domain.SampleRepository
import javax.inject.Inject

class SampleRepositoryImpl @Inject internal constructor(
    private val pokemonApiClient: PokemonApiClient
) : SampleRepository {

    override suspend fun fetchPokemonCharactersList() {
        val characers = pokemonApiClient.fetchCharacters(0, 10)
        println(characers)
    }
}