package com.aniketkadam.distancesorter.distancecalculator

import com.aniketkadam.distancesorter.distancecalculator.data.Coordinates
import com.aniketkadam.distancesorter.distancecalculator.data.Customer
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.Test

class DistanceCalculatorViewModelTest {

    @ExperimentalCoroutinesApi
    @Test
    fun `correctly finds the customers within the minimum distance and orders them`() =
        runBlockingTest {
            val repo = mockk<DistanceViewModelContract.Repository> {
                every { getCoordinatesToMeasureDistanceFrom() } returns Coordinates(
                    53.339428,
                    -6.257664
                )
                every { getAllCustomers() } returns listOf(
                    Customer(Coordinates(53.339428, -6.257664), 3, "Mark lives at the office"),
                    Customer(Coordinates(53.338428, -6.257674), 1, "Joe"),
                    Customer(Coordinates(90.339428, -20.257664), 0, "Too Far"),
                    Customer(Coordinates(53.339528, -6.257464), 4, "Closeby")
                )
                every { getMinimumDistance() } returns 100.0
            }

            val vm = DistanceCalculatorViewModel(repo)

            val expectedOrder = listOf(
                Customer(Coordinates(53.338428, -6.257674), 1, "Joe"),
                Customer(Coordinates(53.339428, -6.257664), 3, "Mark lives at the office"),
                Customer(Coordinates(53.339528, -6.257464), 4, "Closeby")
            )
            assertThat(vm.getCustomersWithinMinimumDistance(), equalTo(expectedOrder))
        }
}