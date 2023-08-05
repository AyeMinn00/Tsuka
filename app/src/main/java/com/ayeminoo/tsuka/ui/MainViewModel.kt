package com.ayeminoo.tsuka.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayeminoo.tsuka.constants.Constants.DEFAULT_BASE_CURRENCY
import com.ayeminoo.tsuka.constants.Constants.DEFAULT_INPUT
import com.ayeminoo.tsuka.domain.CurrencyRepository
import com.ayeminoo.tsuka.models.Currency
import com.ayeminoo.tsuka.utils.CurrencyConverter
import dagger.hilt.android.lifecycle.HiltViewModel
import java.math.RoundingMode
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo: CurrencyRepository
) : ViewModel() {

    private var rates: List<Currency> = emptyList()
    private val _baseCurrency = MutableStateFlow(DEFAULT_BASE_CURRENCY)
    val baseCurrency = _baseCurrency.asStateFlow()

    private val _amount = MutableStateFlow(DEFAULT_INPUT)
    val amount = _amount.asStateFlow()

    private val _currencies = MutableStateFlow<List<Currency>>(emptyList())
    val currencies = _currencies.asStateFlow()

    init {
        viewModelScope.launch {
            repo.currencies.collect { value ->
                rates = value
                onUpdateCurrencies(rates)
            }
        }
        viewModelScope.launch {
            repo.getBaseCurrency().collect { currentBaseCurrency ->
                _baseCurrency.update { currentBaseCurrency }
                onUpdateCurrencies(currentBaseCurrency)
            }
        }
        refreshData()
    }

    private fun onUpdateCurrencies(list: List<Currency>) {
        convertAmount(base = _baseCurrency.value, amount = _amount.value, data = list)
    }

    private fun onUpdateCurrencies(base: String) {
        convertAmount(base, amount = _amount.value, data = rates)
    }

    fun refreshData() {
        viewModelScope.launch {
            repo.refreshCurrencyFromNetwork()
        }
    }

    fun convertAmount(amount: String) {
        _amount.update { amount }
        convertAmount(base = _baseCurrency.value, amount = _amount.value, data = rates)
    }

    private fun convertAmount(base: String, amount: String, data: List<Currency>) {
        val newData: List<Currency> = if (amount.isBlank()) {
            emptyList()
        } else {
            CurrencyConverter(5, RoundingMode.HALF_UP).convert2(base, amount, data)
        }
        _currencies.update { newData }
    }

}