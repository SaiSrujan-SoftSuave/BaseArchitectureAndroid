package com.saibabui.baseapplicationstructure.presentation.coin_detail

import com.saibabui.baseapplicationstructure.domain.model.CoinDetails

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coin: CoinDetails?=null,
    val error: String = ""
)
