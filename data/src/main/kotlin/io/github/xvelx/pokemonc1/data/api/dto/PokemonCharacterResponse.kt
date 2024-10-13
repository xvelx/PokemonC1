package io.github.xvelx.pokemonc1.data.api.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.net.URL

data class PokemonCharacterResponse(
    @JsonProperty("count") val count: Int,
    @JsonProperty("next") val next: URL?,
    @JsonProperty("previous") val previous: URL?,
    @JsonProperty("results") val results: List<PokemonCharacter>
)

data class PokemonCharacter(
    @JsonProperty("name") val name: String,
    @JsonProperty("url") val url: String
)