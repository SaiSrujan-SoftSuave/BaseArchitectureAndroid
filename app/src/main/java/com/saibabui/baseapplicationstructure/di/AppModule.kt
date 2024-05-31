package com.saibabui.baseapplicationstructure.di

import android.app.Application
import com.saibabui.baseapplicationstructure.CoinApplication
import com.saibabui.baseapplicationstructure.common.NetworkManager
import com.saibabui.baseapplicationstructure.data.remote.CoinPaprikaApi
import com.saibabui.baseapplicationstructure.data.repository.CoinRepositoryImpl
import com.saibabui.baseapplicationstructure.domain.repository.CoinRepository
import com.saibabui.baseapplicationstructure.domain.usecases.get_coins.GetCoinsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import com.saibabui.baseapplicationstructure.common.Preference


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePaprikaApi(): CoinPaprikaApi {
        return NetworkManager.apiService()
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api: CoinPaprikaApi): CoinRepository {
        return CoinRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideGetCoinsUseCase(coinRepository: CoinRepository): GetCoinsUseCase {
        return GetCoinsUseCase(coinRepository)
    }

    @Provides
    @Singleton
    fun proviedeSharedPrefs(context : Application): Preference {
        return Preference(context)
    }
}