package com.example.phone_new.modelo.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "phone_detail_table")
class PhoneDetailEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val price: Int,
    val image: String,
    val description: String,
    val lastPrice: Int,
    val credit: Boolean,
)