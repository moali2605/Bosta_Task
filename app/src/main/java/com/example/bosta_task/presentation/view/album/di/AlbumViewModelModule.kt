package com.example.bosta_task.presentation.view.album.di

import com.example.bosta_task.data.repo.Repository
import com.example.bosta_task.domain.usecase.AlbumUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object AlbumViewModelModule {
    @Provides
    @ViewModelScoped
    fun provideAlbumUseCase(repository: Repository): AlbumUseCase {
        return AlbumUseCase(repository)
    }
}