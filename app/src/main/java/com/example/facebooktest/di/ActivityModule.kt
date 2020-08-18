package com.example.facebooktest.di

import com.example.facebooktest.features.main.MainActivity
import com.example.facebooktest.di.scope.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [])
    abstract fun mainActivityInjector(): MainActivity

}