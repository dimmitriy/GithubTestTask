package com.example.facebooktest.di

import com.example.facebooktest.api.RxSingleSchedulers
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(
    includes = [
        NetworkModule::class,
        ApiModule::class
    ]
)

abstract class AppModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        @Singleton
        fun providesScheduler(): RxSingleSchedulers = RxSingleSchedulers.DEFAULT

    }

}