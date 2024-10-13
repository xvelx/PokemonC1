package io.github.xvelx.pokemonc1.util

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

context(TestScope)
@OptIn(ExperimentalCoroutinesApi::class)
fun <T> Flow<T>.collectInBackground(): List<T> {
    val emittedStates = mutableListOf<T>()
    backgroundScope.launch(UnconfinedTestDispatcher()) {
        collect(emittedStates::add)
    }

    return emittedStates
}

@OptIn(ExperimentalCoroutinesApi::class)
class CoroutineTestRule : TestWatcher() {

    override fun starting(description: Description?) {
        Dispatchers.setMain(Dispatchers.Unconfined)
        super.starting(description)
    }

    override fun finished(description: Description?) {
        super.finished(description)
        Dispatchers.resetMain()
    }
}
