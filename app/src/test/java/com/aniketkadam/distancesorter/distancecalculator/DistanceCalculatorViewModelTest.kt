package com.aniketkadam.distancesorter.distancecalculator

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.aniketkadam.distancesorter.RxImmediateSchedulerRule
import com.aniketkadam.distancesorter.distancecalculator.data.Coordinates
import com.aniketkadam.distancesorter.distancecalculator.data.Customer
import com.jraska.livedata.test
import io.mockk.every
import io.mockk.mockk
import org.junit.Rule
import org.junit.Test

class DistanceCalculatorViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val rxImmediateScheduler = RxImmediateSchedulerRule()

    @Test
    fun `correctly finds the customers within the minimum distance and orders them`() {
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

            assertThat(
                vm.customersWithinMinDistance.getOrAwaitValue(time = 30, numValues = 2),
                equalTo<Lce>(Lce.Content(expectedOrder))
            )
        }
}