package com.aniketkadam.distancesorter.distancecalculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aniketkadam.distancesorter.distancecalculator.data.Customer
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DistanceCalculatorViewModel(private val repo: DistanceViewModelContract.Repository) :
    ViewModel() {

    private val disposable = CompositeDisposable()

    private val _customersWithinMinDistance: MutableLiveData<Lce> = MutableLiveData()
    val customersWithinMinDistance: LiveData<Lce> = _customersWithinMinDistance

    init {
        beginLoadingCustomerData()
    }

    private fun beginLoadingCustomerData() {
        disposable.add(
            Observable.just<Lce>(Lce.Content(getCustomersWithinMinimumDistance()))
                .startWith(Lce.Loading)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    _customersWithinMinDistance.value = it
                }
        )
    }

    private fun getCustomersWithinMinimumDistance(): List<Customer> {
        val origin = repo.getCoordinatesToMeasureDistanceFrom()
        val minimumDistance = repo.getMinimumDistance()

        return WithinMinimumDistanceUsecase().execute(
            origin,
            minimumDistance,
            repo.getAllCustomers()
        )
    }

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }
}

sealed class Lce {
    object Loading : Lce()
    data class Content(val customers: List<Customer>) : Lce()
    // No error since none is possible yet
}