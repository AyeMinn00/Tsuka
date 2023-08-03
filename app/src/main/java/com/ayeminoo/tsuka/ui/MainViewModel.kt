package com.ayeminoo.tsuka.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayeminoo.tsuka.data.api.model.DataState
import com.ayeminoo.tsuka.data.api.model.toDomainModel
import com.ayeminoo.tsuka.domain.ConvertCurrencyUseCase
import com.ayeminoo.tsuka.models.Currency
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(
    private val convertCurrencyUseCase: ConvertCurrencyUseCase
) : ViewModel() {

    private val _currencies = MutableStateFlow<List<Currency>>(emptyList())
    val currencies = _currencies.asStateFlow()

    init {
        convertCurrency()
    }

    private fun convertCurrency() {
        viewModelScope.launch {
            when (val rates = convertCurrencyUseCase(Unit)) {
                is DataState.Success -> _currencies.update { rates.data.toDomainModel() }
                is DataState.Error -> Unit
            }
        }
    }

}