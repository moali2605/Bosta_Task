package com.example.bosta_task.data.remote_source

import com.example.bosta_task.data.model.album.AlbumResponse
import com.example.bosta_task.data.model.photo.PhotoResponse
import com.example.bosta_task.data.model.user.UserDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class RemoteSourceImp @Inject constructor(private val retrofitInterface: RetrofitInterface) :RemoteSourceInterface  {
    override suspend fun getUserById(id: String): Flow<UserDto> {
        return flowOf( retrofitInterface.getUserById(id))
    }

    override suspend fun getAlbumByUserId(id: String): Flow<AlbumResponse> {
        return flowOf(retrofitInterface.getAlbumByUserId(id))
    }

    override suspend fun getPhotosByAlbumId(id: String): Flow<PhotoResponse> {
        return flowOf(retrofitInterface.getPhotoByAlbumId(id))
    }
}