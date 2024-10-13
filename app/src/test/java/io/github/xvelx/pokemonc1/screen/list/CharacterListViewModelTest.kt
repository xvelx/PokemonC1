package io.github.xvelx.pokemonc1.screen.list

import io.github.xvelx.pokemonc1.domain.repository.PokemonPagingSource
import io.github.xvelx.pokemonc1.util.CoroutineTestRule
import io.github.xvelx.pokemonc1.util.collectInBackground
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import strikt.api.expectThat
import strikt.assertions.isA
import strikt.assertions.isEqualTo
import strikt.assertions.last

class CharacterListViewModelTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private val pokemonPagingSource: PokemonPagingSource = mockk()
    private val listViewModel = CharacterListViewModel(pokemonPagingSource)

    @Test
    fun `verify select intent posts navigate side effect`() = runTest {
        val emittedValues = listViewModel.sideEffect.collectInBackground()

        listViewModel.sendIntent(CharacterListIntent.Select(5))

        expectThat(emittedValues).last()
            .isA<CharacterListSideEffect.NavigateToDetail>()
            .get { characterId }.isEqualTo(5)
    }
}