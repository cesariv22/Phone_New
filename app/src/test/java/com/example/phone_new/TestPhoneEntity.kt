package com.example.phone_new

import com.example.phone_new.modelo.local.entities.PhoneEntity
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class TestPhoneEntity {

    private lateinit var phoneEntity: PhoneEntity

    @Before
    fun setup() {
        phoneEntity = PhoneEntity(
            id = "1",
            name = "prueba test",
            price = 1,
            image = "image",
        )
    }

    @Test
    fun testPhone(){
        //Verificar valores asignados
        assert(phoneEntity.id=="1")
        assert(phoneEntity.name=="prueba test")
        assert(phoneEntity.price==1)
        assert(phoneEntity.image=="image")
    }
}