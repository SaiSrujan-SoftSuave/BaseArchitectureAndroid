package com.saibabui.baseapplicationstructure.presentation.coin_list

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.saibabui.baseapplicationstructure.domain.model.Coin
import com.saibabui.baseapplicationstructure.presentation.Screen
import com.saibabui.baseapplicationstructure.presentation.coin_list.components.CoinListItem
import dagger.hilt.android.lifecycle.HiltViewModel

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun CoinListScreen(
    navController: NavController,
    viewModel: CoinListViewModel = hiltViewModel()
) {
    val state =
        viewModel.coinListState.collectAsState().value
    Box(modifier = Modifier.fillMaxSize()) {


        if(state.coins.isNotEmpty()){
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state.coins) { coin: Coin ->
                CoinListItem(coin = coin, onItemClick = {
                    navController.navigate(Screen.CoinDetailScreen.route + "/${coin.id}")
                })

            }
        }
        }
        if (state.error.isNotBlank()){
            Text(text = state.error,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
                )
        }
        if (state.isLoading){
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    }
}