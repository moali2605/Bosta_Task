package com.example.bosta_task.presentation.view.album.view

import com.example.bosta_task.presentation.model.PhotoUiDto

sealed class AlbumState {
    data class Display(
        val photoUiDto: List<PhotoUiDto> = listOf(),
        val filteredPhotos: List<PhotoUiDto> = listOf(),
        val loading: Boolean = false
    ) : AlbumState()

    data class Failure(val errorMsg: String = "") : AlbumState()
}