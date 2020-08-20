package com.github.test.di

import com.github.test.features.main.MainActivity
import com.github.test.di.scope.ActivityScope
import com.github.test.features.repos.ReposActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [])
    abstract fun mainActivityInjector(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [])
    abstract fun reposActivityInjector(): ReposActivity

}