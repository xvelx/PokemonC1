package io.github.xvelx.pokemonc1.screen.detail

import io.github.xvelx.pokemonc1.domain.repository.PokemonRepository
import io.github.xvelx.pokemonc1.util.CoroutineTestRule
import io.github.xvelx.pokemonc1.util.collectInBackground
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import strikt.api.expectThat
import strikt.assertions.hasSize
import strikt.assertions.isA
import strikt.assertions.last

class CharacterDetailViewModelTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private val pokemonRepository: PokemonRepository = mockk()
    private val detailViewModel = CharacterDetailViewModel(pokemonRepository)

    @Test
    fun `verify load intent request detail and updates the state`() = runTest {
        val emittedStates = detailViewModel.uiState.collectInBackground()
        coEvery {
            pokemonRepository.fetchCharacterDetail(1)
        } returns Result.success(mockk())

        detailViewModel.sendIntent(CharacterDetailIntent.Load(1))

        coVerify(exactly = 1) {
            pokemonRepository.fetchCharacterDetail(1)
        }
        expectThat(emittedStates).hasSize(2)
            .last()
            .isA<CharacterDetailUiState.Present>()
    }
}

