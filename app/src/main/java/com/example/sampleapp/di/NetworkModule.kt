package com.example.sampleapp.di

import com.example.sampleapp.network.ApiEndpoint
import com.example.sampleapp.repository.GitHubRepository
import com.example.sampleapp.repository.GitHubRepositoryImpl
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
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        val okHttpBuilder = OkHttpClient.Builder()
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        okHttpBuilder.addInterceptor(httpLoggingInterceptor)

        return Retrofit.Builder().baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpBuilder.build())
            .build()

    }

    @Singleton
    @Provides
    fun providesApi(retrofit: Retrofit): ApiEndpoint {
        return retrofit.create(ApiEndpoint::class.java)
    }

    @Singleton
    @Provides
    fun providesRepository(apiEndpoint: ApiEndpoint): GitHubRepository {
        return GitHubRepositoryImpl(apiEndpoint)
    }
}