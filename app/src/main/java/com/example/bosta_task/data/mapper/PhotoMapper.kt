package com.example.bosta_task.data.mapper

import com.example.bosta_task.data.model.photo.PhotoItem
import com.example.bosta_task.data.model.photo.PhotoResponse
import com.example.bosta_task.domain.model.photo.PhotoDomainDto

fun PhotoItem.toPhotoDomainDto(): PhotoDomainDto =
    PhotoDomainDto(
        albumId = this.albumId,
        id = this.id,
        thumbnailUrl = this.thumbnailUrl,
        title = this.title,
        url = this
            .url
    )

fun PhotoResponse.toPhotoDomainResponse(): List<PhotoDomainDto> = this.map {
    it.toPhotoDomainDto()
}