package com.ayeminoo.tsuka.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayeminoo.tsuka.domain.ConvertCurrencyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(
    private val convertCurrencyUseCase: ConvertCurrencyUseCase
) : ViewModel() {

    fun convertCurrency(){
        viewModelScope.launch {
            val rates = convertCurrencyUseCase(Unit)
        }
    }

}