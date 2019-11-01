package com.aniketkadam.distancesorter.distancecalculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aniketkadam.distancesorter.distancecalculator.data.Customer
import kotlinx.coroutines.launch

class DistanceCalculatorViewModel(private val repo: DistanceViewModelContract.Repository) :
    ViewModel() {

    private val _customersWithinMinDistance: MutableLiveData<Lce> = MutableLiveData()
    val customersWithinMinDistance: LiveData<Lce> = _customersWithinMinDistance

    private val withinMinimumDistanceUseCase = WithinMinimumDistanceUsecase()

    init {
        beginLoadingCustomerData()
    }

    private fun beginLoadingCustomerData() {
        _customersWithinMinDistance.value = Lce.Loading
        viewModelScope.launch {
            _customersWithinMinDistance.postValue(Lce.Content(getCustomersWithinMinimumDistance()))
        }
    }

    private suspend fun getCustomersWithinMinimumDistance(): List<Customer> {
        val origin = repo.getCoordinatesToMeasureDistanceFrom()
        val minimumDistance = repo.getMinimumDistance()

        return withinMinimumDistanceUseCase.execute(origin, minimumDistance, repo.getAllCustomers())
    }

}

sealed class Lce {
    object Loading : Lce()
    data class Content(val customers: List<Customer>) : Lce()
    // No error since none is possible yet
}