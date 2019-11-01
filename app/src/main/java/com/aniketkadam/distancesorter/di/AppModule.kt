package com.aniketkadam.distancesorter.di

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.android.support.DaggerApplication

@Module
abstract class AppModule {
    @Binds
    abstract fun provideContext(application: DaggerApplication): Context

}