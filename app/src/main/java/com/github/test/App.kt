package com.github.test

import android.app.Activity
import android.app.Application
import com.github.test.di.AppComponent
import com.github.test.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class App: Application(), HasActivityInjector {

    private val component: AppComponent by lazy {
        DaggerAppComponent.builder()
            .context(this)
            .build()
    }

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingAndroidInjector

}