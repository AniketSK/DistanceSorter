package com.aniketkadam.distancesorter.distancecalculator

import androidx.lifecycle.ViewModel
import com.aniketkadam.distancesorter.distancecalculator.data.Customer

class DistanceCalculatorViewModel(private val repo: DistanceViewModelContract.Repository) :
    ViewModel() {

    private val withinMinimumDistanceUseCase = WithinMinimumDistanceUsecase()

    suspend fun getCustomersWithinMinimumDistance(): List<Customer> {
        val origin = repo.getCoordinatesToMeasureDistanceFrom()
        val minimumDistance = repo.getMinimumDistance()

        return withinMinimumDistanceUseCase.execute(origin, minimumDistance, repo.getAllCustomers())
    }

}
