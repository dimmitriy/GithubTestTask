package com.github.test.di

import com.github.test.api.RxObservableSchedulers
import com.github.test.api.RxSingleSchedulers
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
        fun providesSingleScheduler(): RxSingleSchedulers = RxSingleSchedulers.DEFAULT

        @JvmStatic
        @Provides
        @Singleton
        fun providesObservableScheduler(): RxObservableSchedulers = RxObservableSchedulers.DEFAULT

    }

}