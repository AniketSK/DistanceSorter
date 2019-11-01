package com.aniketkadam.distancesorter.distancecalculator.distancesorter.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aniketkadam.distancesorter.distancecalculator.DistanceCalculatorViewModel
import com.aniketkadam.distancesorter.distancecalculator.data.CustomerRepository
import javax.inject.Inject

class DistanceViewModelFactory @Inject constructor(private val repository: CustomerRepository) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = with(modelClass) {
        when {
            isAssignableFrom(DistanceCalculatorViewModel::class.java) -> DistanceCalculatorViewModel(
                repository
            )
            else -> throw IllegalArgumentException("The factory $canonicalName does not know how to create the class $modelClass")
        }
    } as T

}