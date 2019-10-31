package com.aniketkadam.distancesorter.distancecalculator.data

import com.google.gson.Gson
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.Test

class CustomerTest {

    @Test
    fun `customer names deserialize correctly`() {
        val sampleJson =
            "{\"latitude\": \"52.986375\", \"user_id\": 12, \"name\": \"Christina McArdle\", \"longitude\": \"-6.043701\"}"
        val response = Gson().fromJson(sampleJson, CustomerResponse::class.java)

        assertThat(
            response.toCustomer(), equalTo(
                Customer(
                    Coordinates(52.986375, -6.043701),
                    12,
                    "Christina McArdle"
                )
            )
        )
    }
}