package com.ayeminoo.tsuka.ui.basecurrency

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayeminoo.tsuka.domain.CurrencyRepository
import com.ayeminoo.tsuka.models.Currency
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

@HiltViewModel
class BaseCurrencySelectionViewModel @Inject constructor(
    private val repo: CurrencyRepository
) : ViewModel() {

    val currencies: Flow<List<Currency>> =
        repo.getBaseCurrency().combine(repo.currencies) { baseCurrency, currenciesData ->
            currenciesData.map {
                Currency(
                    currencyCode = it.currencyCode,
                    amount = it.amount,
                    isBase = it.currencyCode == baseCurrency
                )
            }
        }

    fun selectBaseCurrency(cur: String) {
        viewModelScope.launch {
            repo.setBaseCurrency(cur)
        }
    }

}