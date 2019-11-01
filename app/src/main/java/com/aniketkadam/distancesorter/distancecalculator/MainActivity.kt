package com.aniketkadam.distancesorter.distancecalculator

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
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
        vm.customersWithinMinDistance.observe(this, Observer {
            renderData(it)
        })
    }

    private fun initRecyclerView() {
        customerList.adapter = adapter
    }

    private fun renderData(it: Lce?): Unit = when (it) {
        is Lce.Loading -> emptyView.visibility = View.VISIBLE
        is Lce.Content -> {
            emptyView.visibility = View.GONE
            adapter.submitList(it.customers)
        }
        null -> {
        }
    }
}
