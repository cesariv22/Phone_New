package com.example.phone_new.modelo.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "phone_list_table")
data class PhoneEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val price: Int,
    val image: String,
)