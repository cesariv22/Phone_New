package com.example.phone_new.modelo.remote.fromInternet

data class PhoneDetail (
    val id: String,
    val name: String,
    val price: Int,
    val image: String,
    val description: String,
    val lastPrice: Int,
    val credit: Boolean,
)