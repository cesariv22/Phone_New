package com.example.phone_new

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.phone_new.view.MainActivity
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentedTest {

    @Test
    fun testShowPhones() {
        val context = androidx.test.platform.app.InstrumentationRegistry.getInstrumentation().targetContext
        val intent = Intent(context, MainActivity::class.java)

        intent.putExtra("id", "1")
        intent.putExtra("name", "prueba")
        intent.putExtra("price", 100)
        intent.putExtra("image", "url")

        val activityScenario = ActivityScenario.launch<MainActivity>(intent)
        activityScenario.onActivity { activity ->
            assertNotNull(activity)

            assertEquals("1",activity.intent.getStringExtra("id"))
            assertEquals("prueba",activity.intent.getStringExtra("name"))
            assertEquals(100,activity.intent.getIntExtra("price", 1))
            assertEquals("url",activity.intent.getStringExtra("image"))
        }
    }

}