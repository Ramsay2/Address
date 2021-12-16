package com.ramsay.addresssearch.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ramsay.addresssearch.R
import com.ramsay.addresssearch.databinding.ItemLayoutBinding
import com.ramsay.addresssearch.remote.response.Address
import java.util.ArrayList

class AddressAdapter : RecyclerView.Adapter<AddressAdapter.AddressViewHolder>() {

        var addressList = ArrayList<Address>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val layoutBinding: ItemLayoutBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_layout, parent, false)

            return AddressViewHolder(layoutBinding)
    }

    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {
        holder.getData(addressList[position])

    }

    override fun getItemCount(): Int {
        return addressList.size
    }

    inner class AddressViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun getData(address: Address) {
            binding.addressList = address

        }
    }
}