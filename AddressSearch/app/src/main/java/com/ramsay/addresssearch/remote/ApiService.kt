package com.ramsay.addresssearch.remote

import com.ramsay.addresssearch.remote.response.AddressDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("compassLocation/rest/address/autocomplete?queryString=airtel")
  suspend  fun getResponse(
        @Query("queryString") queryString: String,
        @Query("city") city: String
    ) :AddressDTO
}