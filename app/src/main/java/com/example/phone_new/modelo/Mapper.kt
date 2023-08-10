package com.example.phone_new.modelo

import com.example.phone_new.modelo.local.entities.PhoneDetailEntity
import com.example.phone_new.modelo.local.entities.PhoneEntity
import com.example.phone_new.modelo.remote.fromInternet.PhoneDetail
import com.example.phone_new.modelo.remote.fromInternet.Phones


fun fromInternetPhonesEntity( phonesList: List<Phones>) :List<PhoneEntity>{
    return phonesList.map {
        PhoneEntity(
            id=it.id,
            name= it.name,
            price = it.price,
            image = it.image,
        )
    }
}

fun fromInternetPhoneDetailEntity (phone: PhoneDetail) :PhoneDetailEntity {
    return PhoneDetailEntity(
        id=phone.id,
        name= phone.name,
        price = phone.price,
        image = phone.image,
        description = phone.description,
        lastPrice = phone.lastPrice,
        credit = phone.credit,
    )
}




