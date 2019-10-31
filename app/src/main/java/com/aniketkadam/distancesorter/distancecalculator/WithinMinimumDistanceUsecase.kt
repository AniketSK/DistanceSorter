package com.aniketkadam.distancesorter.distancecalculator

import androidx.annotation.VisibleForTesting
import com.aniketkadam.distancesorter.distancecalculator.data.Coordinates
import com.aniketkadam.distancesorter.distancecalculator.data.Customer
import java.math.BigDecimal
import java.math.RoundingMode

private const val equatorialRadius: Double = 6378.137
private const val polarRadius: Double = 6356.752
private const val meanEarthRadius: Double = (1.0 / 3.0) * (2 * equatorialRadius + polarRadius)

class WithinMinimumDistanceUsecase {

    @VisibleForTesting
    fun calculateDistance(pointA: Coordinates, pointB: Coordinates): Double {
        TODO()
    }

    @VisibleForTesting
    fun isWithinMinimumDistance(pointA: Coordinates, pointB: Coordinates, minimumDistance: Double) =
        calculateDistance(pointA, pointB) <= minimumDistance

    @VisibleForTesting
    fun roundToErrorMargin(number: Double) = BigDecimal(number).setScale(
        1,
        RoundingMode.HALF_EVEN
    ) // Error margin is 0.5km for a 100km radius

    fun execute(origin: Coordinates, minimumDistance: Double, customers: List<Customer>) =
        customers.filter { isWithinMinimumDistance(origin, it.location, minimumDistance) }
}

