package com.ayeminoo.tsuka.data.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.ayeminoo.tsuka.constants.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PrefStore(
    private val context: Context
) {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("tuska_pref")
        private val BASE_CURRENCY_KEY = stringPreferencesKey("base_currency")
    }

    val getBaseCurrency: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[BASE_CURRENCY_KEY] ?: Constants.DEFAULT_BASE_CURRENCY
    }

    suspend fun saveBaseCurrency(baseCurrency: String) {
        context.dataStore.edit { preferences ->
            preferences[BASE_CURRENCY_KEY] = baseCurrency
        }
    }

}