package com.example.bosta_task.domain.usecase

import com.example.bosta_task.domain.model.photo.PhotoDomainDto
import com.example.bosta_task.domain.repo.RepositoryInterface
import com.example.bosta_task.util.Response
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AlbumUseCase @Inject constructor(private val repository: RepositoryInterface) {
    suspend fun getPhotosByAlbumId(id: String): Flow<Response<List<PhotoDomainDto>>> {
        return repository.getPhotoByAlbumId(id)
    }
}