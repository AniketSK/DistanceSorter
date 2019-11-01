package com.aniketkadam.distancesorter.distancecalculator

import androidx.annotation.VisibleForTesting
import com.aniketkadam.distancesorter.distancecalculator.data.Coordinates
import com.aniketkadam.distancesorter.distancecalculator.data.Customer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Math.toRadians
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.acos
import kotlin.math.cos
import kotlin.math.sin

private const val equatorialRadius: Double = 6378.137
private const val polarRadius: Double = 6356.752
private const val meanEarthRadius: Double = (1.0 / 3.0) * (2 * equatorialRadius + polarRadius)

/**
 * Given a list of customers, the origin and the minimum required distance from the origin,
 * return a list of customers within distance, sorted by userId.
 */
class WithinMinimumDistanceUsecase {

    @VisibleForTesting
    fun calculateDistance(pointA: Coordinates, pointB: Coordinates): Double {
        val lambda1: Double = toRadians(pointA.longitude)
        val phi1: Double = toRadians(pointA.latitude)

        val lambda2: Double = toRadians(pointB.longitude)
        val phi2: Double = toRadians(pointB.latitude)

        val deltaLambda = lambda1 - lambda2
        val centralAngle =
            acos(sin(phi1) * sin(phi2) + cos(phi1) * cos(phi2) * cos(deltaLambda))

        return meanEarthRadius * centralAngle
    }

    @VisibleForTesting
    fun isWithinMinimumDistance(pointA: Coordinates, pointB: Coordinates, minimumDistance: Double) =
        calculateDistance(pointA, pointB) <= minimumDistance

    @VisibleForTesting
    fun roundToErrorMargin(number: Double): BigDecimal = BigDecimal(number).setScale(
        1,
        RoundingMode.HALF_EVEN
    ) // Error margin is 0.5km for a 100km radius

    suspend fun execute(origin: Coordinates, minimumDistance: Double, customers: List<Customer>) =
        withContext(Dispatchers.IO) {
        customers.filter { isWithinMinimumDistance(origin, it.location, minimumDistance) }
            .sortedBy { it.userId }
        }
}

