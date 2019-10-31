package com.aniketkadam.distancesorter.distancecalculator

import androidx.annotation.VisibleForTesting
import com.aniketkadam.distancesorter.distancecalculator.data.Coordinates
import com.aniketkadam.distancesorter.distancecalculator.data.Customer
import java.lang.Math.toRadians
import java.math.BigDecimal
import java.math.RoundingMode

import kotlin.math.acos
import kotlin.math.cos
import kotlin.math.sin

private const val equatorialRadius: Double = 6378.137
private const val polarRadius: Double = 6356.752
private const val meanEarthRadius: Double = (1.0 / 3.0) * (2 * equatorialRadius + polarRadius)

class WithinMinimumDistanceUsecase {

    @VisibleForTesting
    fun calculateDistance(pointA: Coordinates, pointB: Coordinates): Double {
        val lambda1: Double = toRadians(pointA.longitude)
        val theta1: Double = toRadians(pointA.latitude)

        val lambda2: Double = toRadians(pointB.longitude)
        val theta2: Double = toRadians(pointB.latitude)

        val deltaLambda = lambda1 - lambda2
        val centralAngle =
            acos(sin(theta1) * sin(theta2) + cos(theta1) * cos(theta2) * cos(deltaLambda))

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

    fun execute(origin: Coordinates, minimumDistance: Double, customers: List<Customer>) =
        customers.filter { isWithinMinimumDistance(origin, it.location, minimumDistance) }
}

