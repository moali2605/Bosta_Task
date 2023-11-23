package com.example.bosta_task.data.repo

import com.example.bosta_task.data.mapper.toAlbumDomainResponse
import com.example.bosta_task.data.mapper.toPhotoDomainResponse
import com.example.bosta_task.data.mapper.toUserDomainModel
import com.example.bosta_task.data.remote_source.RemoteSourceInterface
import com.example.bosta_task.domain.model.album.AlbumDomainDto
import com.example.bosta_task.domain.model.photo.PhotoDomainDto
import com.example.bosta_task.domain.model.user.UserDomainDto
import com.example.bosta_task.domain.repo.RepositoryInterface
import com.example.bosta_task.util.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class Repository @Inject constructor(private val remoteSourceInterface: RemoteSourceInterface) :
    RepositoryInterface {
    override suspend fun getUserById(id: String): Flow<Response<UserDomainDto>> {
        return try {
            remoteSourceInterface.getUserById(id).map {
                Response.Success(it.toUserDomainModel())
            }
        } catch (e: Exception) {
            flowOf(Response.Failure(e.message?:"Unknown Exception"))
        }
    }

    override suspend fun getAlbumByUserId(id: String): Flow<Response<List<AlbumDomainDto>>> {
        return try {
            remoteSourceInterface.getAlbumByUserId(id).map {
                Response.Success(it.toAlbumDomainResponse())
            }
        }catch (e:Exception){
            flowOf(Response.Failure(e.message?:"Unknown Exception"))
        }
    }

    override suspend fun getPhotoByAlbumId(id: String): Flow<Response<List<PhotoDomainDto>>> {
        return try {

            remoteSourceInterface.getPhotosByAlbumId(id).map {
                Response.Success(it.toPhotoDomainResponse())
            }
        }catch (e:Exception){
            flowOf(Response.Failure(e.message?:"Unknown Exception"))
        }
    }
}