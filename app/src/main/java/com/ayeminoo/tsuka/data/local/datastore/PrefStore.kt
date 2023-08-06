package com.ayeminoo.tsuka.data.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.ayeminoo.tsuka.constants.Constants
import com.ayeminoo.tsuka.constants.Constants.EMPTY_STRING
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PrefStore(
    private val context: Context
) {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("tuska_pref")
        private val BASE_CURRENCY_KEY = stringPreferencesKey("base_currency")
        private val UPDATED_TIMESTAMP_KEY = stringPreferencesKey("time_stamp")
    }

    val getBaseCurrency: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[BASE_CURRENCY_KEY] ?: Constants.DEFAULT_BASE_CURRENCY
    }

    suspend fun saveBaseCurrency(baseCurrency: String) {
        context.dataStore.edit { preferences ->
            preferences[BASE_CURRENCY_KEY] = baseCurrency
        }
    }

    val getUpdatedTimeStamp: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[UPDATED_TIMESTAMP_KEY] ?: EMPTY_STRING
    }

    suspend fun saveUpdatedTimeStamp(timeStamp: String) {
        context.dataStore.edit { preferences ->
            preferences[UPDATED_TIMESTAMP_KEY] = timeStamp
        }
    }

}