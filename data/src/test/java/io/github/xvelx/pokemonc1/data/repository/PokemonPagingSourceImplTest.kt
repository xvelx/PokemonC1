package io.github.xvelx.pokemonc1.data.repository

import androidx.paging.PagingSource
import io.github.xvelx.pokemonc1.domain.model.PokemonCharacter
import io.github.xvelx.pokemonc1.domain.repository.PokemonRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test
import strikt.api.expectThat
import strikt.assertions.isA
import strikt.assertions.isEqualTo
import strikt.assertions.isNull

class PokemonPagingSourceImplTest {

    private val pokemonRepository: PokemonRepository = mockk()
    private val pagingSourceImpl = PokemonPagingSourceImpl(pokemonRepository)

    @Test
    fun `verify load forms offset based on page number and load size`() = runTest {
        val response = listOf<PokemonCharacter>(mockk())
        coEvery {
            pokemonRepository.fetchCharacters(40, 20)
        } returns Result.success(response)

        val result = pagingSourceImpl.load(
            params = PagingSource.LoadParams.Append(2, 20, true)
        )

        expectThat(result)
            .isA<PagingSource.LoadResult.Page<Int, PokemonCharacter>>()
            .and {
                get { data }.isEqualTo(response)
                get { prevKey }.isNull()
                get { nextKey }.isEqualTo(3)
            }

        coVerify(exactly = 1) {
            pokemonRepository.fetchCharacters(40, 20)
        }
    }
}
