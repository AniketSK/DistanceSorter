package com.aniketkadam.distancesorter.di

import com.aniketkadam.distancesorter.distancecalculator.MainActivity
import com.aniketkadam.distancesorter.distancecalculator.distancesorter.di.MainModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun provideMainActivity(): MainActivity
}