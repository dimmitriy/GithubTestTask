package com.github.test.di

import com.github.test.features.users.UsersActivity
import com.github.test.di.scope.ActivityScope
import com.github.test.features.userdetails.UserDetailsActivity
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