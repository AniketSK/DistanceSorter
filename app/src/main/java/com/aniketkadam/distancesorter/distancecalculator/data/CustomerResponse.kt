package com.aniketkadam.distancesorter.distancecalculator.data

import com.google.gson.annotations.SerializedName

data class CustomerResponse(
    @SerializedName("latitude") private val latitude: Double,
    @SerializedName("longitude") private val longitude: Double,
    @SerializedName("user_id") private val userId: Int,
    @SerializedName("name") private val name: String
) {
    fun toCustomer(): Customer = Customer(Coordinates(latitude, longitude), userId, name)
}