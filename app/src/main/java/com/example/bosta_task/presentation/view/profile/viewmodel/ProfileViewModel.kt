package com.example.bosta_task.presentation.view.profile.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bosta_task.domain.usecase.ProfileUseCase
import com.example.bosta_task.presentation.mapper.toAlbumUiDto
import com.example.bosta_task.presentation.mapper.toUserUiDto
import com.example.bosta_task.presentation.view.profile.view.ProfileState
import com.example.bosta_task.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val useCase: ProfileUseCase) : ViewModel() {


    private val _profileState: MutableStateFlow<ProfileState.Display> =
        MutableStateFlow(ProfileState.Display())
    val profileState = _profileState.asStateFlow()
    private val _errorState: MutableSharedFlow<ProfileState.Failure> = MutableSharedFlow()
    val errorState = _errorState.asSharedFlow()

    fun getUserById() {
        _profileState.update {
            it.copy(loading = true)
        }
        viewModelScope.launch {
            val id = (1..10).random()
            useCase.getUserById("$id").collectLatest {

                when (it) {
                    is Response.Success -> {
                        it.data?.let { user ->
                            _profileState.update {
                                it.copy(userUiDto = user.toUserUiDto(), loading = false)
                            }
                        }
                    }

                    is Response.Failure -> {
                        _profileState.update {
                            it.copy(loading = false)
                        }
                        it.error?.let { error ->
                            _errorState.emit(ProfileState.Failure(error))
                        }
                    }
                }
            }
            useCase.getAlbumByUserId("$id").collectLatest { response ->
                when (response) {
                    is Response.Success -> {
                        response.data?.let {
                            _profileState.update { state ->
                                state.copy(
                                    albums = response.data.map { it.toAlbumUiDto() },
                                    loading = false
                                )
                            }
                        }
                    }

                    is Response.Failure -> {
                        response.error?.let { errorMessage ->
                            _errorState.emit(ProfileState.Failure(errorMessage))
                        }
                        _profileState.update { it.copy(loading = false) }
                    }
                }
            }
        }
    }
}