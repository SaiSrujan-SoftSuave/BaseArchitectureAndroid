package com.saibabui.baseapplicationstructure.domain.usecases.get_coins


import com.saibabui.baseapplicationstructure.common.NetworkResult
import com.saibabui.baseapplicationstructure.data.remote.dto.toCoin
import com.saibabui.baseapplicationstructure.domain.model.Coin
import com.saibabui.baseapplicationstructure.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val coinRepository: CoinRepository,
) {
    operator fun invoke(): Flow<NetworkResult<List<Coin>>> {
        return flow {
            try {
                emit(NetworkResult.Loading())
                val coins = coinRepository.getCoins().map {
                    it.toCoin()
                }
                emit(NetworkResult.Success(coins))
            } catch (e: IOException) {
                emit(NetworkResult.Error(e.localizedMessage))
            } catch (e: IOException) {
                emit(NetworkResult.Error(e.localizedMessage))
            }
        }
    }
}