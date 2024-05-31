package com.saibabui.baseapplicationstructure.presentation.coin_detail

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saibabui.baseapplicationstructure.common.Constants
import com.saibabui.baseapplicationstructure.common.NetworkResult
import com.saibabui.baseapplicationstructure.domain.usecases.get_coin.GetCoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase,
    private val safeStateHandle: SavedStateHandle
): ViewModel() {
    private val _state= mutableStateOf(CoinDetailState())
    val state: State<CoinDetailState> = _state
    init {
        safeStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let { coinId -> getCoinDetail(coinId) }
    }
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    private fun getCoinDetail(coinId:String){
         getCoinUseCase(coinId).onEach {networkResult ->
             when(networkResult){
                 is NetworkResult.Error -> {
                    _state.value = CoinDetailState(error= networkResult.message?:"unexpected error check your internet")
                 }
                 is NetworkResult.Loading -> {
                     _state.value = CoinDetailState(isLoading = true)
                 }
                 is NetworkResult.Success -> {
                     _state.value=
                         CoinDetailState(coin = networkResult.data)
                 }
             }
         }.launchIn(viewModelScope)
    }
}