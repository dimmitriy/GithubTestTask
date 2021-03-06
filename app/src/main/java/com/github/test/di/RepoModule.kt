package com.github.test.di

import com.github.test.repository.SearchRepository
import com.github.test.repository.SearchRepositoryImpl
import com.github.test.repository.UserRepository
import com.github.test.repository.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepoModule {

    @Binds
    @Singleton
    abstract fun provideSearchRepo(repo: SearchRepositoryImpl): SearchRepository

    @Binds
    @Singleton
    abstract fun provideUserRepo(repo: UserRepositoryImpl): UserRepository

}