package com.example.bosta_task.presentation.mapper

import com.example.bosta_task.domain.model.album.AlbumDomainDto
import com.example.bosta_task.presentation.model.album.AlbumUiDto

fun AlbumDomainDto.toAlbumUiDto(): AlbumUiDto = AlbumUiDto(id = this.id, title = this.title)