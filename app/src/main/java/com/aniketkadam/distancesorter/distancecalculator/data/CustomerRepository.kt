package com.aniketkadam.distancesorter.distancecalculator.data

import com.aniketkadam.distancesorter.distancecalculator.DistanceViewModelContract
import javax.inject.Inject


class CustomerRepository @Inject constructor(private val customerApi: CustomerApi) :
    DistanceViewModelContract.Repository {

    private val dublinOfficeCoordinates = Coordinates(53.339428, -6.257664)

    override fun getCoordinatesToMeasureDistanceFrom(): Coordinates = dublinOfficeCoordinates

    override fun getMinimumDistance(): Double = 100.0

    override fun getAllCustomers(): List<Customer> = customerApi.getCustomerData()

}