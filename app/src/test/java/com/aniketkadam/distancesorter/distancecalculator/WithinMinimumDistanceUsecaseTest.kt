package com.aniketkadam.distancesorter.distancecalculator

import com.aniketkadam.distancesorter.distancecalculator.data.Coordinates
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.Test

class WithinMinimumDistanceUsecaseTest {

    val calculator = WithinMinimumDistanceUsecase()

    @Test
    fun `distance between two points is calculated correctly`() {
        val testValues = listOf(
            DistanceTestAnswers(
                Coordinates(53.339428, -6.257664),
                Coordinates(52.986375, -6.043701),
                41.76878232016648
            )
        )

        testValues.forEach {
            val distance = calculator.calculateDistance(
                it.pointA, it.pointB
            )

            val calculatedDistanceToErrorMargin = calculator.roundToErrorMargin(distance)
            val expectedValue = calculator.roundToErrorMargin(it.expectedDistance)
            assertThat(calculatedDistanceToErrorMargin, equalTo(expectedValue))
        }
    }

}

private data class DistanceTestAnswers(
    val pointA: Coordinates,
    val pointB: Coordinates,
    val expectedDistance: Double
)
