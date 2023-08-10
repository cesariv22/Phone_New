package com.example.phone_new.modelo.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.phone_new.modelo.local.entities.PhoneDetailEntity
import com.example.phone_new.modelo.local.entities.PhoneEntity

@Dao
interface PhoneNewDao {

    //Insertar lista de teléfonos
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPhones(listPhones: List<PhoneEntity>)

    //Seleccionar listado de teléfonos
    @Query("SELECT * FROM phone_list_table ORDER BY id ASC")
    fun getAllPhones(): LiveData<List<PhoneEntity>>

    //Inserta un teléfono
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhoneDetail(phone: PhoneDetailEntity)

    @Query("SELECT * FROM phone_detail_table WHERE id=:id")
    fun getPhoneDetailById(id: String): LiveData<PhoneDetailEntity>

}