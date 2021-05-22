package com.example.cleanarchitecturedemo.viewmodels

import android.util.Log
import androidx.lifecycle.*
import com.example.data.api.OnBoardDataRepository
import com.example.data.model.OnBoardData
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class OnBoardViewModel(private val repository: OnBoardDataRepository) : ViewModel() {

    private val _internalState = MutableLiveData(InternalState(getDefaultViewState()))
    val viewState : LiveData<ViewState> = _internalState.map {
        it.viewState
    }
    private val state : InternalState
        get() = requireNotNull(_internalState.value)

    private val channel = Channel<OnBoardEvent>(Channel.UNLIMITED)

    init {
        channel
            .receiveAsFlow()
            .catch { Log.d(TAG, "event error $it") }
            .onEach {
                Log.d(TAG, "event $it")
                when(it) {
                    is OnBoardEvent.Init -> handleInit(it)
                    is OnBoardEvent.NextClicked -> handleNextClicked(it)
                }
            }
            .launchIn(viewModelScope)
    }

    private fun updateState(state: InternalState) {
        _internalState.value = state
    }

    private fun handleInit(event : OnBoardEvent.Init) {
        viewModelScope.launch {
            updateState(state.copy(
                viewState = state.viewState.copy(
                    processingViewState = ProcessingViewState.Show
                )))

            val onBoardData = repository.getData(event.viewId)

            updateState(state.copy(
                viewState = state.viewState.copy(
                    processingViewState = ProcessingViewState.Hide,
                    contentViewState = ContentViewState.Show(onBoardData)
                )))
        }
    }

    private fun handleNextClicked(event: OnBoardEvent.NextClicked) {
        TODO("Not yet implemented")
    }

    private fun getDefaultViewState() : ViewState {
        return ViewState(ProcessingViewState.Hide, ContentViewState.Hide)
    }

    fun handleEvent(event : OnBoardEvent) {
        channel.offer(event)
    }

    private data class InternalState(val viewState: ViewState)

    data class ViewState(
        val processingViewState: ProcessingViewState,
        val contentViewState: ContentViewState)

    sealed class OnBoardEvent {
        data class Init(val viewId : Int) : OnBoardEvent()
        data class NextClicked(val currentViewId : Int) : OnBoardEvent()
    }

    sealed class ProcessingViewState {
        object Hide : ProcessingViewState()
        object Show : ProcessingViewState()
    }

    sealed class ContentViewState {
        object Hide : ContentViewState()
        data class Show(val data : OnBoardData) : ContentViewState()
    }

    companion object {
        const val TAG = "OnBoardViewModel"
    }
}



