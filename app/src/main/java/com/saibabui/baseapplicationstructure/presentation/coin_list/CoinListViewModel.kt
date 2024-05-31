package com.saibabui.baseapplicationstructure.presentation.coin_list

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saibabui.baseapplicationstructure.common.NetworkResult
import com.saibabui.baseapplicationstructure.domain.repository.CoinRepository
import com.saibabui.baseapplicationstructure.domain.usecases.get_coins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase, private val coinRepository: CoinRepository
) : ViewModel() {
    private val _coinListState = MutableStateFlow(CoinListState())
    val coinListState: StateFlow<CoinListState> = _coinListState

    init {
       getCoins()
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    private fun getCoins(){
        getCoinsUseCase().onEach {networkResult ->
            when(networkResult){
                is NetworkResult.Error -> {
                    _coinListState.value = CoinListState(error = networkResult.message?:"unexpected error check your internet")
                }
                is NetworkResult.Loading -> {
                    _coinListState.value = CoinListState(isLoading = true)
                }
                is NetworkResult.Success -> {
                    _coinListState.value = CoinListState(coins = networkResult.data ?: emptyList())
                }
            }

        }.launchIn(viewModelScope)

    }
}