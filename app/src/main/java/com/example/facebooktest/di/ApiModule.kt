package com.example.facebooktest.di

import com.example.facebooktest.api.GitHubApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
abstract class ApiModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        @Singleton
        internal fun provideMxApi(retrofit: Retrofit): GitHubApiService =
            retrofit.create(GitHubApiService::class.java)

    }

}