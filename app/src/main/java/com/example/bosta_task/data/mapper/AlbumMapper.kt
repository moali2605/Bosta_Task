package com.example.bosta_task.data.mapper

import com.example.bosta_task.data.model.album.AlbumItem
import com.example.bosta_task.data.model.album.AlbumResponse
import com.example.bosta_task.domain.model.album.AlbumDomainDto

fun AlbumItem.toAlbumDomainDto(): AlbumDomainDto =
    AlbumDomainDto(id = this.id ?: 1, title = this.title ?: "")

fun AlbumResponse.toAlbumDomainResponse() : List<AlbumDomainDto> =
    this.map { it.toAlbumDomainDto() }