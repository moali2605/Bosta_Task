package com.example.bosta_task.domain.usecase

import com.example.bosta_task.domain.model.album.AlbumDomainDto
import com.example.bosta_task.domain.model.photo.PhotoDomainDto
import com.example.bosta_task.domain.model.user.UserDomainDto
import com.example.bosta_task.domain.repo.RepositoryInterface
import com.example.bosta_task.util.Response
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UseCaseImp @Inject constructor(private val repository: RepositoryInterface) {
    suspend fun getUserById(id: String): Flow<Response<UserDomainDto>> {
        return repository.getUserById(id)
    }

    suspend fun getAlbumByUserId(id: String): Flow<Response<List<AlbumDomainDto>>> {
        return repository.getAlbumByUserId(id)
    }

    suspend fun getPhotosByAlbumId(id: String): Flow<Response<List<PhotoDomainDto>>> {
        return repository.getPhotoByAlbumId(id)
    }
}