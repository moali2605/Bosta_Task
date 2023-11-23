package com.example.bosta_task.presentation.view.album.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bosta_task.domain.usecase.UseCaseImp
import com.example.bosta_task.presentation.mapper.toPhotoUiDto
import com.example.bosta_task.presentation.view.profile.viewmodel.ProfileState
import com.example.bosta_task.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumDetailsViewModel @Inject constructor(private val useCase: UseCaseImp) : ViewModel() {
    private val _albumState: MutableStateFlow<AlbumState.Display> =
        MutableStateFlow(AlbumState.Display())
    val albumState = _albumState.asStateFlow()
    private val _errorState: MutableSharedFlow<ProfileState.Failure> = MutableSharedFlow()
    val errorState = _errorState.asSharedFlow()

    fun getPhotoByAlbumId(id: String) {
        _albumState.update {
            it.copy(loading = true)
        }
        viewModelScope.launch(Dispatchers.IO) {
            useCase.getPhotosByAlbumId(id).collectLatest { response ->
                when (response) {
                    is Response.Success -> {
                        response.data?.let { photo ->
                            _albumState.update {
                                it.copy(
                                    photoUiDto = response.data.map {
                                        it.toPhotoUiDto()
                                    },
                                    filteredPhotos = response.data.map {
                                        it.toPhotoUiDto()
                                    },
                                    loading = false
                                )
                            }
                        }
                    }

                    is Response.Failure -> {
                        response.error?.let { errorMessage ->
                            _errorState.emit(ProfileState.Failure(errorMessage))
                        }
                        _albumState.update { it.copy(loading = false) }
                    }
                }
            }
        }
    }

    fun searchByTitle(key: String) {
        _albumState.update { state ->
            state.copy(
                filteredPhotos = _albumState.value.filteredPhotos.filter { photo ->
                    photo.title?.startsWith(key.lowercase(), true) ?: true
                }
            )
        }
    }
}