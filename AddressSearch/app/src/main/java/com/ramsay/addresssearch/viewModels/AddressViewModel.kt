package com.ramsay.addresssearch.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ramsay.addresssearch.di.Module
import com.ramsay.addresssearch.remote.ApiService
import com.ramsay.addresssearch.remote.response.AddressDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddressViewModel : ViewModel() {
    var addressDTO: MutableLiveData<AddressDTO> = MutableLiveData()
    val response = Module.network().create(ApiService::class.java)
    fun makeApiCall(query: String) {

        CoroutineScope(Dispatchers.IO).launch {
            response.getResponse("airtel", query)
            addressDTO.postValue(response.getResponse("airtel", query))
        }

    }

    fun getAddressFromApi(): LiveData<AddressDTO> {

        return addressDTO

    }
}