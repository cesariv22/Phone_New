package com.example.phone_new.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.phone_new.modelo.PhoneNewRepository
import com.example.phone_new.modelo.local.dataBase.PhoneDataBase
import com.example.phone_new.modelo.local.entities.PhoneDetailEntity
import com.example.phone_new.modelo.local.entities.PhoneEntity
import kotlinx.coroutines.launch

class PhoneViewModel (application: Application) : AndroidViewModel(application) {

    // conexión repository
    private val repository: PhoneNewRepository

    // entidades
    private val phoneDetailLiveData = MutableLiveData<PhoneDetailEntity>()

    // para seleccionar
    private var idSelected: String = "-1"


    init {
        // tiene la instancia de la bd el dao y lo entregamos el repository
        val bd = PhoneDataBase.getDataBase(application)
        val phoneNewDao = bd.getPhoneNewDao()

        repository = PhoneNewRepository(phoneNewDao)

        // llamo al método del repository

        viewModelScope.launch {
            repository.fetchPhone()
        }
    }

    // listado de los elementos
    fun getPhoneList(): LiveData<List<PhoneEntity>> = repository.phonesListLiveData

    // para obtener un teléfono por id desde lo que se selecciono
    fun getPhoneDetail(): LiveData<PhoneDetailEntity> = phoneDetailLiveData


    // desde el segundo fragmento le paso la seleccion
    fun getPhoneDetailByIdFromInternet(id: String) = viewModelScope.launch {
        val phoneDetail = repository.fetchPhoneDetail(id)
        phoneDetail?.let {
            phoneDetailLiveData.postValue(it)
        }
    }
}


