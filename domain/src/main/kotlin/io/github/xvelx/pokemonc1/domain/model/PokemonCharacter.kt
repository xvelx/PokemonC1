package io.github.xvelx.pokemonc1.domain.model

import java.net.URL

data class PokemonCharacter(
    val id: Int,
    val name: String,
    val imageUrl: String,
)

data class PokemonCharacterDetail(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val weight: Long,
    val height: Long,
    val abilities: List<String>,
    val states: Map<String, Long>,
    val cries: URL
)
