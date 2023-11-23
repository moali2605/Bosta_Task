package com.example.bosta_task.presentation.model

import java.io.Serializable

data class PhotoUiDto(
    val albumId: Int?,
    val id: Int?,
    val thumbnailUrl: String?,
    val title: String?,
    val url: String?
):Serializable
