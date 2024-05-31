package com.saibabui.baseapplicationstructure.domain.usecases.get_coin

import android.net.http.HttpException
import android.os.Build
import android.os.ext.SdkExtensions
import com.example.cryptoapplication.data.remote.dto.toCoinDetail
import com.saibabui.baseapplicationstructure.common.NetworkResult
import com.saibabui.baseapplicationstructure.domain.model.CoinDetails
import com.saibabui.baseapplicationstructure.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val coinRepository: CoinRepository,

) {
    operator fun invoke(coinId: String): Flow<NetworkResult<CoinDetails>> = flow {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R && SdkExtensions.getExtensionVersion(
                Build.VERSION_CODES.S
            ) >= 7
        ) {
            try {
                emit(NetworkResult.Loading())
                val coinDetails = coinRepository.getCoinById(coinId = coinId).toCoinDetail()
                emit(NetworkResult.Success(coinDetails))

            } catch (e: HttpException) {
                emit(NetworkResult.Error(e.localizedMessage))

            } catch (e: IOException) {
                emit(NetworkResult.Error(e.localizedMessage))
            }
        }

    }
}