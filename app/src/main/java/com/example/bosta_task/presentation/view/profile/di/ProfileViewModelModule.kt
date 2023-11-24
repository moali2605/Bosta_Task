package com.example.bosta_task.presentation.view.profile.di

import com.example.bosta_task.data.repo.Repository
import com.example.bosta_task.domain.usecase.ProfileUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ProfileViewModelModule {
    @Provides
    @ViewModelScoped
    fun provideProfileUseCase(repository: Repository): ProfileUseCase {
        return ProfileUseCase(repository)
    }
}