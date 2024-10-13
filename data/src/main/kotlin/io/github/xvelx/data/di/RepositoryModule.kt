package io.github.xvelx.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.xvelx.data.repository.SampleRepositoryImpl
import io.github.xvelx.domain.SampleRepository

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindSampleRepository(impl: SampleRepositoryImpl): SampleRepository
}