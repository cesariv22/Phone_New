package com.example.phone_new.modelo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.phone_new.modelo.local.PhoneNewDao
import com.example.phone_new.modelo.local.entities.PhoneDetailEntity
import com.example.phone_new.modelo.remote.RetrofitClient

class PhoneNewRepository(private val phoneNewDao: PhoneNewDao) {

    //Retrofit Client
    private val networkService = RetrofitClient.retrofitInstance()

    // dao listado
    val phonesListLiveData = phoneNewDao.getAllPhones()

    // un elemento
    val phoneDetailLiveData = MutableLiveData<PhoneDetailEntity>()


    suspend fun fetchPhone() {
        val service = kotlin.runCatching { networkService.fetchPhoneList() }
        service.onSuccess {
            when (it.code()) {
                in 200..299 -> it.body()?.let {
                    Log.d("Phones", it.toString())
                    phoneNewDao.insertAllPhones(fromInternetPhonesEntity(it))
                }

                else -> Log.d("Repo", "${it.code()}-${it.errorBody()}")
            }
            service.onFailure {
                Log.e("Error", "${it.message}")
            }
        }
    }


    suspend fun fetchPhoneDetail(id: String): PhoneDetailEntity? {
        val service = kotlin.runCatching { networkService.fetchPhoneDetail(id) }
        return service.getOrNull()?.body()?.let { phoneDetail ->
            // guardo los datos que viene del mapper y luego se los paso a dao directo
            val phoneDetailEntity = fromInternetPhoneDetailEntity(phoneDetail)
            //inserto los detalles de los curso DEL REPOSITORIO
            phoneNewDao.insertPhoneDetail(phoneDetailEntity)
            phoneDetailEntity
        }
    }
}