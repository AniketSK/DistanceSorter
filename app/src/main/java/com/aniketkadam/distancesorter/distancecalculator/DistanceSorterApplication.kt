package com.aniketkadam.distancesorter.distancecalculator

import com.aniketkadam.distancesorter.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class DistanceSorterApplication : DaggerApplication() {

    val appComponent by lazy {
        DaggerAppComponent.factory().create(this)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = appComponent

}