package com.aniketkadam.distancesorter.distancecalculator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.aniketkadam.distancesorter.R
import com.aniketkadam.distancesorter.databinding.CustomerDisplayBinding
import com.aniketkadam.distancesorter.distancecalculator.data.Customer

class CustomerAdapter : ListAdapter<Customer, CustomerViewHolder>(EMPTY_CALLBACK) {

    var inflater: LayoutInflater? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerViewHolder {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.context)
        }

        val binding = DataBindingUtil.inflate<CustomerDisplayBinding>(
            inflater!!,
            R.layout.customer_display,
            parent,
            false
        )

        return CustomerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomerViewHolder, position: Int) =
        holder.bind(getItem(position))


    companion object EMPTY_CALLBACK :
        DiffUtil.ItemCallback<Customer>() { // This isn't needed because the items never change on screen.
        override fun areItemsTheSame(
            oldItem: Customer,
            newItem: Customer
        ): Boolean {
            throw NotImplementedError("Individual items don't change yet. Implement if required.")
        }

        override fun areContentsTheSame(
            oldItem: Customer,
            newItem: Customer
        ): Boolean {
            throw NotImplementedError("Individual items don't change yet. Implement if required.")
        }

    }
}