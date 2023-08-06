package com.ayeminoo.tsuka.data.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.ayeminoo.tsuka.MainCoroutineRule
import com.ayeminoo.tsuka.data.local.db.CurrencyDao
import com.ayeminoo.tsuka.data.local.db.CurrencyDb
import com.ayeminoo.tsuka.data.local.db.toVo
import com.ayeminoo.tsuka.models.Currency
import com.ayeminoo.tsuka.models.toEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CurrencyDaoTest {

    private lateinit var currencyDao: CurrencyDao
    private lateinit var db: CurrencyDb

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, CurrencyDb::class.java)
            .allowMainThreadQueries()
            .build()
        currencyDao = db.currencyDao()
    }

    @After
    fun closeDb() {
        db.clearAllTables()
        db.close()
    }

    @Test
    fun write_read_currency() = runTest {
        val usd = Currency("USD", "0.23")
        val thb = Currency("THB", "1243")
        val data = listOf(usd, thb)
        val entities = data.toEntity()
        currencyDao.insertCurrency(entities)
        val actual = currencyDao.getAllCurrency().first()
        assertEquals(2, actual.size)
        assertEquals(usd, actual[0].toVo())
        assertEquals(thb, actual[1].toVo())
    }

    @Test
    fun insertAgain_shouldReplace() = runTest {
        val usd = Currency("USD", "0.23")
        val updatedUsd = usd.copy(amount = "4.44")
        currencyDao.insertCurrency(listOf(usd).toEntity())
        currencyDao.insertCurrency(listOf(updatedUsd).toEntity())
        val actual = currencyDao.getAllCurrency().first()
        assertEquals(1, actual.size)
        assertEquals(updatedUsd, actual[0].toVo())
    }


}