package io.github.xvelx.pokemonc1.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.xvelx.pokemonc1.data.repository.PokemonPagingSourceImpl
import io.github.xvelx.pokemonc1.data.repository.PokemonRepositoryImpl
import io.github.xvelx.pokemonc1.domain.repository.PokemonPagingSource
import io.github.xvelx.pokemonc1.domain.repository.PokemonRepository

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindPokemonPagingSource(impl: PokemonPagingSourceImpl): PokemonPagingSource

    @Binds
    fun bindPokemonRepository(impl: PokemonRepositoryImpl): PokemonRepository
}
