package com.example.phone_new.modelo.remote

import com.example.phone_new.modelo.remote.fromInternet.PhoneDetail
import com.example.phone_new.modelo.remote.fromInternet.Phones
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PhoneNewApi {

    @GET("products")
        suspend fun fetchPhoneList():Response<List<Phones>>

    @GET("details/{id}")
    suspend fun fetchPhoneDetail(@Path("id") id:String) : Response<PhoneDetail>
}