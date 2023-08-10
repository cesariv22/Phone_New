package com.example.phone_new.modelo.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient{

    companion object{

        private const val BASE_URL ="https://my-json-server.typicode.com/Himuravidal/FakeAPIdata/"

        lateinit var  retrofitInstance : Retrofit

        fun retrofitInstance(): PhoneNewApi{

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return  retrofit.create(PhoneNewApi::class.java)
        }


    }
}