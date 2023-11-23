package com.example.bosta_task.presentation.mapper

import com.example.bosta_task.data.mapper.toPhotoDomainDto
import com.example.bosta_task.domain.model.photo.PhotoDomainDto
import com.example.bosta_task.presentation.model.PhotoUiDto

fun PhotoDomainDto.toPhotoUiDto(): PhotoUiDto = PhotoUiDto(
    albumId = this.albumId,
    id = this.id,
    thumbnailUrl = this.thumbnailUrl,
    title = this.title,
    url = this
        .url
)