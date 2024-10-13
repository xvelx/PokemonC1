package io.github.xvelx.pokemonc1.data.repository

import io.github.xvelx.pokemonc1.data.api.PokemonApiClient
import io.github.xvelx.pokemonc1.data.mapper.PokemonCharacterDetailMapper
import io.github.xvelx.pokemonc1.data.mapper.PokemonCharacterMapper
import io.github.xvelx.pokemonc1.network.AppCoroutineDispatchers
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import org.junit.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import strikt.assertions.isSuccess
import io.github.xvelx.pokemonc1.data.api.dto.PokemonCharacter as CharacterDto
import io.github.xvelx.pokemonc1.data.api.dto.PokemonCharacterDetail as DetailDto
import io.github.xvelx.pokemonc1.domain.model.PokemonCharacter as CharacterModel
import io.github.xvelx.pokemonc1.domain.model.PokemonCharacterDetail as DetailModel

class PokemonRepositoryImplTest {

    private val pokemonApiClient: PokemonApiClient = mockk()
    private val pokemonCharacterMapper: PokemonCharacterMapper = mockk()
    private val pokemonCharacterDetailMapper: PokemonCharacterDetailMapper = mockk()
    private val appCoroutineDispatchers: AppCoroutineDispatchers = mockk {
        every { io } returns Dispatchers.Unconfined
    }
    private val repositoryImpl = PokemonRepositoryImpl(
        pokemonApiClient,
        pokemonCharacterMapper,
        pokemonCharacterDetailMapper,
        appCoroutineDispatchers
    )

    @Test
    fun `verify fetchCharacters calls api and mapper for forming the domain model`() = runTest {
        val chList = listOf<CharacterDto>(mockk())
        val resultList = listOf<CharacterModel>(mockk())
        coEvery { pokemonApiClient.fetchCharacters(21, 20) } returns mockk {
            every { results } returns chList
        }
        every { pokemonCharacterMapper.map(chList) } returns resultList

        val result = repositoryImpl.fetchCharacters(21, 20)

        expectThat(result)
            .isSuccess()
            .isEqualTo(resultList)
        coVerify(exactly = 1) {
            pokemonApiClient.fetchCharacters(21, 20)
            pokemonCharacterMapper.map(chList)
        }
    }

    @Test
    fun `verify fetchCharacterDetail calls api and mapper for forming the domain model`() =
        runTest {
            val detailDto = mockk<DetailDto>()
            val detailModel = mockk<DetailModel>()
            coEvery { pokemonApiClient.fetchCharacterDetail(11) } returns detailDto
            every { pokemonCharacterDetailMapper.map(detailDto) } returns detailModel

            val result = repositoryImpl.fetchCharacterDetail(11)

            expectThat(result)
                .isSuccess()
                .isEqualTo(detailModel)
            coVerify(exactly = 1) {
                pokemonApiClient.fetchCharacterDetail(11)
                pokemonCharacterDetailMapper.map(detailDto)
            }
        }
}
