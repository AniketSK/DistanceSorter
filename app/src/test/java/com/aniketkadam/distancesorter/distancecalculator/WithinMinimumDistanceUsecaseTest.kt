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
            ),
            DistanceTestAnswers(
                Coordinates(53.339428, -6.257664),
                Coordinates(53.0, -7.0),
                62.2
            ),
            DistanceTestAnswers(
                Coordinates(43.677, -79.630),
                Coordinates(-26.133, 28.242),
                13368.8
            ),
            DistanceTestAnswers(
                Coordinates(53.2731, -9.0637),
                Coordinates(-37.6284, 143.7533),
                17301.3
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
