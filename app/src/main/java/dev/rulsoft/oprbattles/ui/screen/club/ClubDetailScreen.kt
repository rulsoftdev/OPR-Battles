package dev.rulsoft.oprbattles.ui.screen.club

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import dev.rulsoft.oprbattles.ui.composable.ClubDetail

@Composable
fun ClubDetailScreen(
    viewModel: ClubDetailViewModel = hiltViewModel()
){
    val state by viewModel.state.collectAsState()

    if (state.loading) {
        CircularProgressIndicator()
    }
    if (state.club != null) {
        ClubDetail(club = state.club!!)
    }
}


