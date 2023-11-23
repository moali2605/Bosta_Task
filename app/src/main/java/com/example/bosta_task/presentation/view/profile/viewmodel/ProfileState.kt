package com.example.bosta_task.presentation.view.profile.viewmodel

import com.example.bosta_task.presentation.model.album.AlbumUiDto
import com.example.bosta_task.presentation.model.user.AddressUiDto
import com.example.bosta_task.presentation.model.user.CompanyUiDto
import com.example.bosta_task.presentation.model.user.GeoUiDto
import com.example.bosta_task.presentation.model.user.UserUiDto

sealed class ProfileState {
    data class Display(
        val userUiDto: UserUiDto = UserUiDto(
            AddressUiDto("", GeoUiDto("",""), "", "", ""),
            CompanyUiDto("", "", ""), "", 1, "", "", "", ""
        ),val albums: List<AlbumUiDto> = listOf(), val loading: Boolean=false
    ) : ProfileState()

    data class Failure(val errorMsg: String = "") : ProfileState()
}
