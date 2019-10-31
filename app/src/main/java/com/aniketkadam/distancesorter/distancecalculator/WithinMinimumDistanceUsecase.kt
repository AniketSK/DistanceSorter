package com.aniketkadam.distancesorter.distancecalculator

import androidx.annotation.VisibleForTesting
import com.aniketkadam.distancesorter.distancecalculator.data.Coordinates
import com.aniketkadam.distancesorter.distancecalculator.data.Customer

class WithinMinimumDistanceUsecase {

    @VisibleForTesting
    fun calculateDistance(pointA: Coordinates, pointB: Coordinates): Double {
        TODO()
    }

    @VisibleForTesting
    fun isWithinMinimumDistance(pointA: Coordinates, pointB: Coordinates, minimumDistance: Double) =
        calculateDistance(pointA, pointB) <= minimumDistance

    fun execute(origin: Coordinates, minimumDistance: Double, customers: List<Customer>) =
        customers.filter { isWithinMinimumDistance(origin, it.location, minimumDistance) }
}