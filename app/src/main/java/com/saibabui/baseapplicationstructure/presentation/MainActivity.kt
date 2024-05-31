package com.saibabui.baseapplicationstructure.presentation

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresExtension
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.cryptoapplication.presentation.ui.theme.CryptoApplicationTheme
import com.saibabui.baseapplicationstructure.R
import com.saibabui.baseapplicationstructure.common.Preference
import com.saibabui.baseapplicationstructure.presentation.coin_detail.CoinDetailScreen
import com.saibabui.baseapplicationstructure.presentation.coin_list.CoinListScreen
import com.saibabui.baseapplicationstructure.presentation.coin_list.CoinListViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var preference: Preference

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CryptoApplicationTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.CoinListScreen.route
                    ) {
                        composable(
                            route = Screen.CoinListScreen.route
                        ) {
                            CoinListScreen(navController = navController)
                        }
                        composable(
                            route = Screen.CoinDetailScreen.route + "/{coinId}"
                        ) {
                            CoinDetailScreen()
                        }
                    }
                }
            }
        }
    }
}