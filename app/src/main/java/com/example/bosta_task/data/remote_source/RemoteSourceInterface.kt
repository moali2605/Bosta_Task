package com.example.bosta_task.data.remote_source

import com.example.bosta_task.data.model.album.AlbumResponse
import com.example.bosta_task.data.model.photo.PhotoResponse
import com.example.bosta_task.data.model.user.UserDto
import kotlinx.coroutines.flow.Flow

interface RemoteSourceInterface {
    suspend fun getUserById(id: String): Flow<UserDto>
    suspend fun getAlbumByUserId(id: String): Flow<AlbumResponse>
    suspend fun getPhotosByAlbumId(id: String): Flow<PhotoResponse>
}