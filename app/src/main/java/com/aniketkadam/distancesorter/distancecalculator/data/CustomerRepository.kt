package com.aniketkadam.distancesorter.distancecalculator.data

import com.aniketkadam.distancesorter.distancecalculator.DistanceViewModelContract


class CustomerRepository : DistanceViewModelContract.Repository {

    private val dublinOfficeCoordinates = Coordinates(53.339428, -6.257664)

    override fun getCoordinatesToMeasureDistanceFrom(): Coordinates = dublinOfficeCoordinates

    override fun getMinimumDistance(): Double = 100.0

    override fun getAllCustomers(): List<Customer> = TODO()

}