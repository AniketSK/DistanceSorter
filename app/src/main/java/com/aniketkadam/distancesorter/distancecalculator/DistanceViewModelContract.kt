package com.aniketkadam.distancesorter.distancecalculator

import com.aniketkadam.distancesorter.distancecalculator.data.Customer

interface DistanceViewModelContract {
    interface Repository {
        fun getAllCustomers(): List<Customer>
    }
}