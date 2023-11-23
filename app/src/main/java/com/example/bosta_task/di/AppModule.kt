package com.example.bosta_task.di

import com.example.bosta_task.data.remote_source.RemoteSourceImp
import com.example.bosta_task.data.remote_source.RemoteSourceInterface
import com.example.bosta_task.data.remote_source.RetrofitInterface
import com.example.bosta_task.data.repo.Repository
import com.example.bosta_task.domain.repo.RepositoryInterface
import com.example.bosta_task.domain.usecase.UseCaseImp
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    companion object {
        @Provides
        @Singleton
        fun providesLoggingInterceptor(): HttpLoggingInterceptor {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
            return logging
        }

        @Provides
        @Singleton
        fun providesOkHttpClient(okHttpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(okHttpLoggingInterceptor)
                .build()
        }


        @Provides
        @Singleton
        fun provideApi(okHttpClient: OkHttpClient): RetrofitInterface {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient)
                .addConverterFactory(
                    GsonConverterFactory.create()
                )
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .build().create(RetrofitInterface::class.java)
        }

        @Provides
        @Singleton
        fun provideUseCase(repository: RepositoryInterface): UseCaseImp {
            return UseCaseImp(repository)
        }
    }

    @Binds
    @Singleton
    abstract fun ProvideRemoteSource(remoteSourceImp: RemoteSourceImp): RemoteSourceInterface

    @Binds
    @Singleton
    abstract fun provideRepository(repository: Repository): RepositoryInterface

}