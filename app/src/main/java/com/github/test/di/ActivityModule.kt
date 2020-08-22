package com.github.test.di

import com.github.test.features.main.UsersActivity
import com.github.test.di.scope.ActivityScope
import com.github.test.features.repos.UserDetailsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [])
    abstract fun mainActivityInjector(): UsersActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [])
    abstract fun reposActivityInjector(): UserDetailsActivity

}