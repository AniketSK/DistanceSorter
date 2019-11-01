package com.aniketkadam.distancesorter.distancecalculator

import android.os.Bundle
import com.aniketkadam.distancesorter.R
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var vm: DistanceCalculatorViewModel
    val adapter: CustomerAdapter = CustomerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        customerList.adapter = adapter
    }

}
