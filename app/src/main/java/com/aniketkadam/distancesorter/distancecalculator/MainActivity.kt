package com.aniketkadam.distancesorter.distancecalculator

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.aniketkadam.distancesorter.R
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var vm: DistanceCalculatorViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
