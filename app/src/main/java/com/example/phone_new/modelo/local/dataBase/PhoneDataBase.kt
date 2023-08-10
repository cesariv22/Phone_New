package com.example.phone_new.modelo.local.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.phone_new.modelo.local.PhoneNewDao
import com.example.phone_new.modelo.local.entities.PhoneDetailEntity
import com.example.phone_new.modelo.local.entities.PhoneEntity


@Database(
    entities = [PhoneEntity::class, PhoneDetailEntity::class],
    version = 1,
    exportSchema = false
)
abstract class PhoneDataBase : RoomDatabase() {
    //Referencia del Dao
    abstract fun getPhoneNewDao(): PhoneNewDao

    companion object {

        @Volatile
        private var INSTANCE: PhoneDataBase? = null

        fun getDataBase(context: Context) : PhoneDataBase{
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PhoneDataBase::class.java, "phone_new")
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}