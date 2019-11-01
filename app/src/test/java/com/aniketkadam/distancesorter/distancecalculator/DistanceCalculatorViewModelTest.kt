package com.aniketkadam.distancesorter.distancecalculator

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.aniketkadam.distancesorter.distancecalculator.data.Coordinates
import com.aniketkadam.distancesorter.distancecalculator.data.Customer
import com.aniketkadam.distancesorter.util.MainCoroutineRule
import com.aniketkadam.distancesorter.util.getOrAwaitValue
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.Rule
import org.junit.Test

class DistanceCalculatorViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

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

            assertThat(
                vm.customersWithinMinDistance.getOrAwaitValue(numValues = 2),
                equalTo<Lce>(Lce.Content(expectedOrder))
            )
        }
}