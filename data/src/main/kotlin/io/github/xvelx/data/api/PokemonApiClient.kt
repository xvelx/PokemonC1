package io.github.xvelx.data.api

import io.github.xvelx.data.api.dto.PokemonCharacterDetail
import io.github.xvelx.data.api.dto.PokemonCharacterResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface PokemonApiClient {

    @GET("pokemon/")
    suspend fun fetchCharacters(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int = 20
    ): PokemonCharacterResponse

    @GET("pokemon/{character_id}/")
    suspend fun fetchCharacterDetail(
        @Path("character_id") characterId: Int
    ): PokemonCharacterDetail

    companion object {
        const val BASE_URL = "https://pokeapi.co/api/v2/"
    }
}
