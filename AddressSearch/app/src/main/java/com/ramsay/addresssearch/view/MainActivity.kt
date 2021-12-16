package com.ramsay.addresssearch.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ramsay.addresssearch.databinding.ActivityMainBinding
import com.ramsay.addresssearch.remote.response.Address
import com.ramsay.addresssearch.viewModels.AddressViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var addressViewModel: AddressViewModel
    private lateinit var addressAdapter: AddressAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        addressViewModel = AddressViewModel()
        setRecyclerview()
        searchAddress()

    }

    private fun setRecyclerview() {

        binding.recyclerview.apply {
            addressAdapter = AddressAdapter()
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = addressAdapter

        }
    }

    private fun searchAddress(){
      binding.etSearchBar .addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {


            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
             if(s.toString().isEmpty()){
                 addressAdapter.addressList.clear()
             }
                getData(s.toString())

            }

            override fun afterTextChanged(s: Editable?) {


            }
        })

    }

    fun getData(query :String){
        addressViewModel.getAddressFromApi().observe(this , Observer {
            addressAdapter.addressList = it.data.addressList as ArrayList<Address>
        })
        addressViewModel.makeApiCall(query)

        addressAdapter.notifyDataSetChanged()
    }
}