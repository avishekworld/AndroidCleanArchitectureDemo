package com.example.cleanarchitecturedemo.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.UserProfile
import com.example.domain.usecases.GetUseProfileUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch


class UseProfileViewModel(private val getUseProfileUseCase: GetUseProfileUseCase) : ViewModel() {

    private val _viewState = MutableLiveData<UserProfileViewState>(UserProfileViewState.InitState)
    val viewState : LiveData<UserProfileViewState> = _viewState
    private val state : UserProfileViewState = requireNotNull(viewState.value)

    private val eventChannel = Channel<UserProfileEvent>(Channel.UNLIMITED)

    init {
        eventChannel
            .receiveAsFlow()
            .onEach {
                when(it) {
                is UserProfileEvent.Init -> initView()
                is UserProfileEvent.GetUserProfile ->  getUserProfile(it.id)
            } }
            .launchIn(viewModelScope)
    }

    fun onEvent(event: UserProfileEvent) {
        eventChannel.offer(event)
    }

    private fun updateViewState(state : UserProfileViewState) {
        _viewState.value = state
    }

    private fun initView() {
        updateViewState(state = UserProfileViewState.InitState)
    }

    private fun getUserProfile(id: Long) {
        updateViewState(state = UserProfileViewState.ShowProcessingDialog("loading"))
        viewModelScope.launch {
            getUseProfileUseCase.run(id).let {
                updateViewState(state = UserProfileViewState.HideProcessingDialog)
                updateViewState(state = UserProfileViewState.ShowUserProfile(it))
            }
        }
    }

}

sealed class UserProfileEvent {
    object Init : UserProfileEvent()
    data class GetUserProfile(val id : Long): UserProfileEvent()
}

sealed class UserProfileViewState {
    object InitState : UserProfileViewState()
    data class ShowProcessingDialog(val msg : String) : UserProfileViewState()
    object HideProcessingDialog : UserProfileViewState()
    data class ShowUserProfile(val userProfile: UserProfile) : UserProfileViewState()
}