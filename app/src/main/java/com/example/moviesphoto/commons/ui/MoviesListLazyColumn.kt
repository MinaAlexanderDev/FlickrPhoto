package com.example.moviesphoto.commons.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.example.moviesphoto.data.model.MoviePhotoState
import com.example.moviesphoto.data.model.Photo
import com.example.moviesphoto.util.Constants
import com.example.moviesphoto.util.Constants.TASK_BOX_TEST_TAG
import com.example.moviesphoto.util.Constants.TASK_LIST_TEST_TAG
import com.example.moviesphoto.util.createMoviePhotoUrl


@Composable
fun MoviesListLazyColumn(
    moviesPhotoPaging: LazyPagingItems<Photo>,
    onClick: (String) -> Unit,
    imageDescription: String,
    moviesPhotoState: State<MoviePhotoState>,
    isMoviePhotoDialogVisible: () -> Unit,

    ) {
    val context = LocalContext.current
    if (moviesPhotoPaging.loadState.refresh is LoadState.Error) {
        Toast.makeText(
            context,
            "Error: " + (moviesPhotoPaging.loadState.refresh as LoadState.Error).error.message,
            Toast.LENGTH_LONG
        ).show()
    }

    if (moviesPhotoPaging.loadState.refresh is LoadState.Loading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .testTag(TASK_BOX_TEST_TAG)
        ) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
    MovieFullSizeImageDialog(
        isVisible = moviesPhotoState.value.isMoviePhotoDialogVisible,
        moviePhotoUrl = moviesPhotoState.value.singleMoviePhotoUrl,
        onDismissRequest = {
            isMoviePhotoDialogVisible()
        },
        imageDescription = imageDescription
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag(TASK_LIST_TEST_TAG),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item { Spacer(modifier = Modifier.padding(4.dp)) }
        itemsIndexed(items = moviesPhotoPaging) { index, movie ->
            if (movie != null) {
                MovieItem(
                    moviePhotoUrl = createMoviePhotoUrl(
                        farm = movie.farm,
                        server = movie.server,
                        id = movie.id,
                        secret = movie.secret
                    ),
                    onClick = {
                        onClick(
                            createMoviePhotoUrl(
                                farm = movie.farm,
                                server = movie.server,
                                id = movie.id,
                                secret = movie.secret
                            )
                        )

                    }, imageDescription = imageDescription

                )
                if ((index + 1) % 5 == 0) {
                    MovieItem(
                        moviePhotoUrl = Constants.ADS_PHOTO_URL,
                        onClick = {
                            onClick(
                                Constants.ADS_PHOTO_URL
                            )
                        }, imageDescription = imageDescription
                    )
                }
            }
        }

        item {
            if (moviesPhotoPaging.loadState.append is LoadState.Loading) {
                CircularProgressIndicator()
            }
        }
    }
}

