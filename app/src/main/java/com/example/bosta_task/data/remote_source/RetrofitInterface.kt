package com.example.bosta_task.data.remote_source

import com.example.bosta_task.data.model.album.AlbumResponse
import com.example.bosta_task.data.model.photo.PhotoResponse
import com.example.bosta_task.data.model.user.UserDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitInterface {
    companion object {
        const val USERS_ENDPOINT = "/users"
        const val ALBUMS_ENDPOINT = "/albums"
        const val PHOTOS_ENDPOINT = "/photos"
    }

    @GET("$USERS_ENDPOINT/{UserId}")
    suspend fun getUserById(@Path("UserId") id: String): UserDto

    @GET(ALBUMS_ENDPOINT)
    suspend fun getAlbumByUserId(@Query("userId") id: String): AlbumResponse

    @GET(PHOTOS_ENDPOINT)
    suspend fun getPhotoByAlbumId(@Query("albumId") id: String): PhotoResponse
}