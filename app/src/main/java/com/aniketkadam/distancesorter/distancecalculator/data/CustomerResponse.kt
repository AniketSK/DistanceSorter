package com.aniketkadam.distancesorter.distancecalculator.data

import com.google.gson.annotations.SerializedName

data class CustomerResponse(
    @SerializedName("latitude") val latitude: Double,
    @SerializedName("longitude") val longitude: Double,
    @SerializedName("user_id") val userId: Int,
    @SerializedName("name") val name: String
) {
    fun toCustomer(): Customer = Customer(Coordinates(latitude, longitude), userId, name)
}