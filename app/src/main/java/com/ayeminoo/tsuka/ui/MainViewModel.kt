package com.ayeminoo.tsuka.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayeminoo.tsuka.constants.Constants.DEFAULT_BASE_CURRENCY
import com.ayeminoo.tsuka.constants.Constants.DEFAULT_INPUT
import com.ayeminoo.tsuka.domain.CurrencyRepository
import com.ayeminoo.tsuka.models.Currency
import com.ayeminoo.tsuka.utils.Calculator
import com.ayeminoo.tsuka.utils.CurrencyConverter
import dagger.hilt.android.lifecycle.HiltViewModel
import java.math.RoundingMode
import java.util.Timer
import java.util.TimerTask
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo: CurrencyRepository
) : ViewModel() {

    companion object {
        private const val SCALE = 4
        private val ROUNDING_MODE = RoundingMode.HALF_UP
        private const val PERIOD: Long = 30 * 60 * 1000
    }

    private val timer = Timer()

    private var rates: List<Currency> = emptyList()
    private val _baseCurrency = MutableStateFlow(DEFAULT_BASE_CURRENCY)
    val baseCurrency = _baseCurrency.asStateFlow()

    private val _amount = MutableStateFlow(DEFAULT_INPUT)
    val amount = _amount.asStateFlow()

    private val _currencies = MutableStateFlow<List<Currency>>(emptyList())
    val currencies = _currencies.asStateFlow()

    private val _lastUpdate = MutableStateFlow("")
    val lastUpdate = _lastUpdate.asStateFlow()

    init {
        collectCurrencies()
        collectBaseCurrency()
        collectLastUpdate()
        refreshData()
        fetchRatesEveryPeriod()
    }

    private fun collectCurrencies() {
        viewModelScope.launch {
            repo.currencies.collect { value ->
                rates = value
                onUpdateCurrencies(rates)
            }
        }
    }

    private fun collectBaseCurrency() {
        viewModelScope.launch {
            repo.getBaseCurrency().collect { currentBaseCurrency ->
                _baseCurrency.update { currentBaseCurrency }
                onUpdateCurrencies(currentBaseCurrency)
            }
        }
    }

    private fun collectLastUpdate() {
        viewModelScope.launch {
            repo.getLastUpdatedDateTime().collect { dateTime ->
                _lastUpdate.update { dateTime }
            }
        }
    }

    private fun fetchRatesEveryPeriod() {
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                refreshData()
            }
        }, 0, PERIOD)
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
        viewModelScope.launch(Dispatchers.IO) {
            val newData: List<Currency> = if (Calculator.nothing(amount)) {
                emptyList()
            } else {
                CurrencyConverter(SCALE, ROUNDING_MODE).convert(base, amount, data)
            }
            _currencies.update { newData }
        }
    }

}