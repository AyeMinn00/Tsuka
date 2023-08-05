package com.ayeminoo.tsuka.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CurrencyEntity::class] , version = 1 , exportSchema = false)
abstract class CurrencyDb : RoomDatabase() {
    abstract fun currencyDao() : CurrencyDao
}