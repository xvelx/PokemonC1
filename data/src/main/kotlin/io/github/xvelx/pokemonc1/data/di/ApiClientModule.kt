package io.github.xvelx.pokemonc1.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.xvelx.pokemonc1.data.api.PokemonApiClient
import io.github.xvelx.pokemonc1.network.ApiClientFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal object ApiClientModule {

    @Singleton
    @Provides
    fun providePokemonApiClient(
        apiClientFactory: ApiClientFactory
    ): PokemonApiClient = apiClientFactory
        .create(PokemonApiClient.BASE_URL, PokemonApiClient::class.java)
}