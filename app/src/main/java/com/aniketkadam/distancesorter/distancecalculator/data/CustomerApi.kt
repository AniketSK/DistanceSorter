package com.aniketkadam.distancesorter.distancecalculator.data

import com.aniketkadam.distancesorter.distancecalculator.distancesorter.di.CUSTOMER_DATA_INPUTSTREAM
import com.google.gson.Gson
import java.io.InputStream
import javax.inject.Inject
import javax.inject.Named

class CustomerApi @Inject constructor(
    @Named(CUSTOMER_DATA_INPUTSTREAM) private val customerFile: InputStream,
    private val gson: Gson
) {

    fun getCustomerData(): List<Customer> = customerFile.bufferedReader().readLines()
        .map { gson.fromJson(it, CustomerResponse::class.java) }
        .map { it.toCustomer() }
}