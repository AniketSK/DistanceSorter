package com.aniketkadam.distancesorter.distancecalculator

import com.aniketkadam.distancesorter.distancecalculator.data.Coordinates
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.Test

class WithinMinimumDistanceUsecaseTest {

    val calculator = WithinMinimumDistanceUsecase()

    @Test
    fun `distance between two points is calculated correctly`() {
        val distance = calculator.calculateDistance(
            Coordinates(53.339428, -6.257664),
            Coordinates(52.986375, -6.043701)
        )

        val calculatedDistanceToErrorMargin = calculator.roundToErrorMargin(distance)
        val expectedValue = calculator.roundToErrorMargin(41.76878232016648)

        assertThat(calculatedDistanceToErrorMargin, equalTo(expectedValue))
    }
}