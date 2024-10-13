package io.github.xvelx.pokemonc1.data.mapper

import io.github.xvelx.pokemonc1.data.api.dto.PokemonCharacter
import org.junit.Test
import strikt.api.expectThat
import strikt.assertions.hasSize
import strikt.assertions.isEqualTo
import strikt.assertions.isNotEmpty
import strikt.assertions.isUpperCase
import strikt.assertions.withFirst

class PokemonCharacterMapperTest {

    private val characterMapper = PokemonCharacterMapper()

    @Test
    fun `verify map extracts characterId from the URL and other information from DTO`() {
        val result = characterMapper.map(
            listOf(
                PokemonCharacter(
                    "bulbasaur",
                    "https://pokeapi.co/api/v2/pokemon/1/"
                )
            )
        )

        expectThat(result).isNotEmpty()
            .hasSize(1)
            .withFirst {
                get { id }.isEqualTo(1)
                get { name }.isUpperCase().isEqualTo("BULBASAUR")
                get { imageUrl }.isEqualTo(
                    "https://raw.githubusercontent.com/PokeAPI/sprites/master/" +
                            "sprites/pokemon/other/official-artwork/1.png"
                )
            }
    }

    @Test
    fun `verify map returns -1 as characterId when extraction fails`() {
        val result = characterMapper.map(
            listOf(
                PokemonCharacter(
                    "bulbasaur",
                    "https://pokeapi.co/api/v2/pokemon/"
                )
            )
        )

        expectThat(result).isNotEmpty()
            .hasSize(1)
            .withFirst {
                get { id }.isEqualTo(-1)
            }
    }
}
