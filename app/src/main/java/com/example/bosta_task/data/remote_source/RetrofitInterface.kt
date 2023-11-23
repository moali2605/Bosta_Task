package com.example.bosta_task.data.remote_source

import com.example.bosta_task.data.model.album.AlbumResponse
import com.example.bosta_task.data.model.photo.PhotoResponse
import com.example.bosta_task.data.model.user.UserDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitInterface {
    @GET("/users/{UserId}")
    suspend fun getUserById(@Path("UserId") id: String): UserDto

    @GET("/albums")
    suspend fun getAlbumByUserId(@Query("userId") id: String): AlbumResponse

    @GET("/photos")
    suspend fun getPhotoByAlbumId(@Query("albumId") id: String): PhotoResponse
}