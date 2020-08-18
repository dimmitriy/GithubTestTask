package com.example.facebooktest.di

import com.example.facebooktest.repository.SearchRepository
import com.example.facebooktest.repository.SearchRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepoModule {

    @Binds
    @Singleton
    abstract fun provideSearchRepo(repo: SearchRepositoryImpl): SearchRepository

}