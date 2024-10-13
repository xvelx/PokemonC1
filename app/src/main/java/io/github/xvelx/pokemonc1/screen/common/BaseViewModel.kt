package io.github.xvelx.pokemonc1.screen.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

interface BaseIntent

interface BaseSideEffect

interface BaseUiState {
    data object Loading : BaseUiState
}

abstract class BaseViewModel<I : BaseIntent, S : BaseSideEffect, U : BaseUiState>(
    initialUiState: U
) : ViewModel() {

    private val _uiState = MutableStateFlow<U>(initialUiState)
    val uiState: Flow<U> = _uiState

    private val _sideEffect = MutableSharedFlow<S>()
    val sideEffect: Flow<S> = _sideEffect

    abstract fun sendIntent(intent: I)

    fun updateUiState(block: (oldState: U) -> U) = _uiState.update(block)

    fun postSideEffect(sideEffect: S) {
        viewModelScope.launch {
            _sideEffect.emit(sideEffect)
        }
    }

    fun launch(block: suspend () -> Unit) = viewModelScope.launch { block() }
}
