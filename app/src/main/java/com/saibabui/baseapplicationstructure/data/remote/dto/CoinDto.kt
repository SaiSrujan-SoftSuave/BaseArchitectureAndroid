package com.saibabui.baseapplicationstructure.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.saibabui.baseapplicationstructure.domain.model.Coin

data class CoinDto(
    val id: String,
    @SerializedName("is_active")
    val isActive: Boolean,
    @SerializedName("is_new")
    val isNew: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
    val type: String
)

fun CoinDto.toCoin(): Coin{
    return Coin(
        id = id,
        name = name,
        isActive = isActive,
        rank = rank,
        symbol = symbol
    )
}