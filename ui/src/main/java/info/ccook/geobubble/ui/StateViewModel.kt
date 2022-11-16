package info.ccook.geobubble.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

abstract class StateViewModel<S, R>(initialState: S) : ViewModel() {

    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<S> = _state.asStateFlow()

    protected fun updateState(result: R) {
        _state.update { currentState ->
            resultToState(currentState, result)
        }
    }

    protected abstract fun resultToState(previousState: S, result: R): S
}
