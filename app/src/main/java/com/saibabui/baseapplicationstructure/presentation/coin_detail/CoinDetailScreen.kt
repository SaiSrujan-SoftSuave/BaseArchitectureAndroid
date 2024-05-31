package com.saibabui.baseapplicationstructure.presentation.coin_detail

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cryptoapplication.data.remote.dto.TeamMember
import com.saibabui.baseapplicationstructure.domain.model.Coin
import com.saibabui.baseapplicationstructure.presentation.Screen
import com.saibabui.baseapplicationstructure.presentation.coin_detail.components.CoinTag
import com.saibabui.baseapplicationstructure.presentation.coin_detail.components.TeamListItem
import com.saibabui.baseapplicationstructure.presentation.coin_list.components.CoinListItem
import dagger.hilt.android.lifecycle.HiltViewModel

@OptIn(ExperimentalLayoutApi::class)
@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun CoinDetailScreen(
    viewModel: CoinDetailViewModel = hiltViewModel()
) {
    val state =
        viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
state.coin.let { coin ->
    LazyColumn(modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(20.dp)) {
        item {
            Row(
                modifier =Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                if (coin != null) {
                    Text(text ="${coin.rank}.${coin.name} (${coin.symbol})",
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.weight(8f)
                    )
                }
                Text(
                    text = if(coin?.isActive == true)"active" else "inactive",
                    color = if(coin?.isActive == true) Color.Green else Color.Red,
                    fontStyle = FontStyle.Italic,
                    textAlign = TextAlign.End,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .weight(2f)
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
            coin?.description?.let { Text(text = it, style = MaterialTheme.typography.bodyMedium) }
            Spacer(modifier = Modifier.height(15.dp))
            Text(text = "Tags", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(15.dp))
            FlowRow(maxItemsInEachRow = 3, modifier = Modifier.fillMaxWidth()) {
             coin?.tags?.forEach{tag ->
                 Box(modifier = Modifier.padding(5.dp)){
                     CoinTag(tag = tag)
                 }
             }
            }
            Spacer(modifier = Modifier.height(15.dp))
        }
        items(coin?.team ?: emptyList()){ item: TeamMember ->
            TeamListItem(teamMember = item,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                )
            Divider()
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