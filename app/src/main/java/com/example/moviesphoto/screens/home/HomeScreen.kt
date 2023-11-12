package com.example.moviesphoto.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.moviephoto.R
import com.example.moviesphoto.commons.ui.MoviesListLazyColumn
import com.example.moviesphoto.data.model.MoviePhotoStateEvent


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun HomeScreen(
) {
    val viewModel: HomeViewModel = hiltViewModel()
    val context = LocalContext.current
    Surface(
        modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()

        ) {
            MoviesListLazyColumn(
                moviesPhotoPaging = viewModel.moviesPhotoList.collectAsLazyPagingItems(),
                onClick = {
                    viewModel.handleEvent(
                        MoviePhotoStateEvent.SingleMoviePhotoEvent(it)
                    )
                },
                imageDescription = context.getString(
                    R.string.image_description
                ),
                moviesPhotoState = viewModel.state.collectAsState(),
                isMoviePhotoDialogVisible = {
                    viewModel.handleEvent(MoviePhotoStateEvent.IsMoviePhotoDialogVisible)
                }
            )
        }
    }

}




