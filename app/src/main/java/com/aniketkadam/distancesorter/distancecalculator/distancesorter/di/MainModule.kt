package com.aniketkadam.distancesorter.distancecalculator.distancesorter.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.aniketkadam.distancesorter.R
import com.aniketkadam.distancesorter.distancecalculator.DistanceCalculatorViewModel
import com.aniketkadam.distancesorter.distancecalculator.MainActivity
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import java.io.InputStream
import javax.inject.Named

const val CUSTOMER_DATA_INPUTSTREAM = "customer_data_inputstream"

@Module
object MainModule {

    @JvmStatic
    @Provides
    fun getDistanceSorterViewModel(activity: MainActivity, factory: DistanceViewModelFactory) =
        ViewModelProvider(activity, factory).get(DistanceCalculatorViewModel::class.java)

    @JvmStatic
    @Provides
    @Named(CUSTOMER_DATA_INPUTSTREAM)
    fun getCustomerFile(context: Context): InputStream =
        context.resources.openRawResource(R.raw.customers)

    @JvmStatic
    @Provides
    fun getGson() = Gson()

}