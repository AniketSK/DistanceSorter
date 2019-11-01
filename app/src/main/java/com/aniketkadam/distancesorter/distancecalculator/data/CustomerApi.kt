package com.aniketkadam.distancesorter.distancecalculator.data

import com.google.gson.Gson
import java.io.InputStream
import javax.inject.Inject

class CustomerApi @Inject constructor(
    private val customerFile: InputStream,
    private val gson: Gson
) {

    fun getCustomerData(): List<Customer> = customerFile.bufferedReader().readLines()
        .map { gson.fromJson(it, CustomerResponse::class.java) }
        .map { it.toCustomer() }
}