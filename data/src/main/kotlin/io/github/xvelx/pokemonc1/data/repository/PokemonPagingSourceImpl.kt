package io.github.xvelx.pokemonc1.data.repository

import androidx.paging.PagingState
import io.github.xvelx.pokemonc1.domain.model.PokemonCharacter
import io.github.xvelx.pokemonc1.domain.repository.PokemonPagingSource
import io.github.xvelx.pokemonc1.domain.repository.PokemonRepository
import javax.inject.Inject

class PokemonPagingSourceImpl @Inject internal constructor(
    private val pokemonRepository: PokemonRepository
) : PokemonPagingSource() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonCharacter> {
        val pageNumber = (params.key ?: 0)
        val offset = pageNumber * params.loadSize
        return pokemonRepository.fetchCharacters(offset, params.loadSize)
            .fold(
                onSuccess = { response ->
                    LoadResult.Page(
                        data = response,
                        prevKey = null,
                        nextKey = if (response.isNotEmpty()) pageNumber + 1 else pageNumber
                    )
                },
                onFailure = {
                    LoadResult.Error(it)
                }
            )
    }

    override fun getRefreshKey(state: PagingState<Int, PokemonCharacter>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
