package com.example.bosta_task.domain.repo


import com.example.bosta_task.domain.model.album.AlbumDomainDto
import com.example.bosta_task.domain.model.photo.PhotoDomainDto
import com.example.bosta_task.domain.model.user.UserDomainDto
import com.example.bosta_task.util.Response
import kotlinx.coroutines.flow.Flow


interface RepositoryInterface {
    suspend fun getUserById(id: String): Flow<Response<UserDomainDto>>
    suspend fun getAlbumByUserId(id: String): Flow<Response<List<AlbumDomainDto>>>
    suspend fun getPhotoByAlbumId(id: String):Flow<Response<List<PhotoDomainDto>>>
}