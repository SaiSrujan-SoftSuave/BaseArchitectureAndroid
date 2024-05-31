package com.saibabui.baseapplicationstructure.domain.repository

import com.example.cryptoapplication.data.remote.dto.CoinDetailDto
import com.saibabui.baseapplicationstructure.data.remote.dto.CoinDto

interface CoinRepository {

    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinById(coinId: String): CoinDetailDto
}