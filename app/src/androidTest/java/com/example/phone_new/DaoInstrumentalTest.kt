package com.example.phone_new

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.phone_new.modelo.local.PhoneNewDao
import com.example.phone_new.modelo.local.dataBase.PhoneDataBase
import com.example.phone_new.modelo.local.entities.PhoneDetailEntity
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class DaoInstrumentalTest {

    private lateinit var phoneNewDao: PhoneNewDao
    private lateinit var db: PhoneDataBase

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<android.content.Context>()
        db = Room.inMemoryDatabaseBuilder(context, PhoneDataBase::class.java).build()
        phoneNewDao = db.getPhoneNewDao()
    }

    @After
    fun shutDown() {
        db.close()
    }

    @Test
    fun insertDetailPhone() = runBlocking {
        val phoneDetail = PhoneDetailEntity(
            "2",
            "phone 2",
            200,
            "url",
            "description",
            180,
            false
        )
        phoneNewDao.insertPhoneDetail(phoneDetail)

            assertThat(phoneDetail.id, equalTo("2"))
            assertThat(phoneDetail.lastPrice, equalTo(180))
        }
    }

