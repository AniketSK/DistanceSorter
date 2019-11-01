package com.aniketkadam.distancesorter.distancecalculator.data

import com.google.gson.Gson
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.Test

class CustomerApiTest {

    @Test
    fun `can retrieve customer data from disk`() {
        val customerFile = javaClass.classLoader?.getResourceAsStream("customers.txt")!!
        val gson = Gson()

        val data = CustomerApi(customerFile, gson).getCustomerData()
        assertThat(data.size, equalTo(32))
    }
}