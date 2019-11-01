package com.aniketkadam.distancesorter.distancecalculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aniketkadam.distancesorter.distancecalculator.data.Customer
import kotlinx.coroutines.launch

class DistanceCalculatorViewModel(private val repo: DistanceViewModelContract.Repository) :
    ViewModel() {

    private val _customersWithinMinDistance: MutableLiveData<List<Customer>> = MutableLiveData()
    val customersWithinMinDistance: LiveData<List<Customer>> = _customersWithinMinDistance

    private val withinMinimumDistanceUseCase = WithinMinimumDistanceUsecase()

    init {
        viewModelScope.launch {
            _customersWithinMinDistance.value = getCustomersWithinMinimumDistance()
        }
    }

    private suspend fun getCustomersWithinMinimumDistance(): List<Customer> {
        val origin = repo.getCoordinatesToMeasureDistanceFrom()
        val minimumDistance = repo.getMinimumDistance()

        return withinMinimumDistanceUseCase.execute(origin, minimumDistance, repo.getAllCustomers())
    }

}
