package com.ayeminoo.tsuka.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayeminoo.tsuka.data.api.model.DataState
import com.ayeminoo.tsuka.data.api.model.LatestJson
import com.ayeminoo.tsuka.data.api.model.toDomainModel
import com.ayeminoo.tsuka.domain.ConvertCurrencyUseCase
import com.ayeminoo.tsuka.models.Currency
import com.ayeminoo.tsuka.utils.CurrencyConverter
import dagger.hilt.android.lifecycle.HiltViewModel
import java.math.RoundingMode
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber

@HiltViewModel
class MainViewModel @Inject constructor(
    private val convertCurrencyUseCase: ConvertCurrencyUseCase
) : ViewModel() {

    private val baseCurrency = "THB"
    private var json: LatestJson? = null

    private val _currencies = MutableStateFlow<List<Currency>>(emptyList())
    val currencies = _currencies.asStateFlow()

    init {
        convertCurrency()
    }

    fun convertCurrency() {
        viewModelScope.launch {
            when (val rates = convertCurrencyUseCase(Unit)) {
                is DataState.Success -> {
                    Timber.e("Success ${rates.data.toDomainModel().size}")
                    json = rates.data
                    _currencies.update {
                        rates.data.toDomainModel()
                    }
                }

                is DataState.Error -> {
                    Timber.e("error ${rates.error}")
                }
            }
        }
    }

    fun convertAmount(amount: String) {
        Timber.e("convert $amount")
        if (amount.isBlank()) return
        json?.let {
            val newData = CurrencyConverter(3 , RoundingMode.HALF_UP).convert2(baseCurrency, amount, it.rates)
            _currencies.update { newData ?: emptyList() }
        }
    }

}