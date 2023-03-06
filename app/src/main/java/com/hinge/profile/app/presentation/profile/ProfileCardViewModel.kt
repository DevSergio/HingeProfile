package com.hinge.profile.app.presentation.profile

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hinge.profile.app.domain.model.User
import com.hinge.profile.app.repository.ProfileListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ProfileCardViewModel @Inject constructor(
    private val profileListRepository: ProfileListRepository
) : ViewModel() {

    private val _users = MutableStateFlow<List<User>?>(null)
    val users = _users.asStateFlow()

    val loading = mutableStateOf(false)

    init {
        loadConfig()
        getUsers()
    }

    private fun loadConfig() {
        profileListRepository.loadConfig().onEach { dataState ->
        }.launchIn(viewModelScope)
    }

    fun getUsers() {
        profileListRepository.loadUsers().onEach { dataState ->
            loading.value = dataState.loading
            _users.value = dataState.data
            loading.value = false
        }.launchIn(viewModelScope)
    }

}