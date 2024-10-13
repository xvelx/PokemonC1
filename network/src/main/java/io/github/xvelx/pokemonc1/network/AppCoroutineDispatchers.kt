package io.github.xvelx.pokemonc1.network

import jakarta.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class AppCoroutineDispatchers @Inject internal constructor() {
    val io: CoroutineDispatcher = Dispatchers.IO
    val main: CoroutineDispatcher = Dispatchers.Main
}
