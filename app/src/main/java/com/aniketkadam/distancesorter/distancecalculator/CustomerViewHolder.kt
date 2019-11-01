package com.aniketkadam.distancesorter.distancecalculator

import androidx.recyclerview.widget.RecyclerView
import com.aniketkadam.distancesorter.databinding.CustomerDisplayBinding
import com.aniketkadam.distancesorter.distancecalculator.data.Customer

class CustomerViewHolder(private val binding: CustomerDisplayBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(customerData: Customer) {
        binding.apply {
            customer = customerData
            executePendingBindings()
        }
    }
}