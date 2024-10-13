package io.github.xvelx.pokemonc1.data.mapper

import io.mockk.every
import io.mockk.mockk
import org.junit.Test
import strikt.api.expectThat
import strikt.assertions.containsExactly
import strikt.assertions.containsKeys
import strikt.assertions.isEqualTo
import io.github.xvelx.pokemonc1.data.api.dto.PokemonCharacterDetail as CharacterDetailDto

class PokemonCharacterDetailMapperTest {

    private val characterDetailMapper = PokemonCharacterDetailMapper()

    @Test
    fun `verify detail models is correctly formed from character detail model`() {
        val detailDto = mockk<CharacterDetailDto> {
            // abilities
            every { abilities } returns listOf(
                mockk {
                    every { ability.name } returns "run-away"
                },
                mockk {
                    every { ability.name } returns "leap"
                }
            )

            every { id } returns 10
            every { name } returns "bulbasaur"
            every { weight } returns 75
            every { height } returns 7

            // stats
            every { stats } returns listOf(
                mockk {
                    every { baseStat } returns 50
                    every { stat.name } returns "hp"
                },
                mockk {
                    every { baseStat } returns 75
                    every { stat.name } returns "strength"
                }
            )

            // cries
            every { cries.latest } returns mockk()
        }

        val result = characterDetailMapper.map(detailDto)

        expectThat(result) {
            get { id }.isEqualTo(10)
            get { name }.isEqualTo("BULBASAUR")
            get { weight }.isEqualTo(75)
            get { height }.isEqualTo(7)
            get { abilities }.containsExactly(listOf("RUN-AWAY", "LEAP"))
            get { states }.containsKeys("HP", "STRENGTH").and {
                get { this["HP"] }.isEqualTo(50)
                get { this["STRENGTH"] }.isEqualTo(75)
            }
        }
    }
}
