package com.example.moviesphoto.commons.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.moviephoto.R
import com.example.moviesphoto.util.Constants.ADS_PHOTO_URL
import com.example.moviesphoto.util.Constants.TASK_FULL_SIZE_PHOTO_TEST_TAG


@Composable
fun MovieFullSizeImageDialog(
    isVisible: Boolean = false,
    moviePhotoUrl: String? = null,
    onDismissRequest: () -> Unit,
    imageDescription: String
) {
    if (isVisible) {
        Dialog(
            onDismissRequest = {
                onDismissRequest()
            },

            ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .testTag(TASK_FULL_SIZE_PHOTO_TEST_TAG),
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)

            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Color.Transparent,
                        ),

                    ) {
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .clickable { onDismissRequest() }) {
                        Image(
                            painter = rememberAsyncImagePainter(
                                ImageRequest.Builder(LocalContext.current).data(
                                    data = moviePhotoUrl,
                                )
                                    .apply(block = fun ImageRequest.Builder.() {
                                        placeholder(R.drawable.ic_placeholder)
                                        crossfade(true)
                                    }).build()
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .clickable {
                                    onDismissRequest()
                                },
                            contentScale = ContentScale.Crop,
                            contentDescription = imageDescription
                        )

                    }

                }
            }
        }
    }

}

@Preview
@Composable
fun IndeterminateCircularIndicatorPreview() {
    MovieFullSizeImageDialog(
        isVisible = true,
        moviePhotoUrl = ADS_PHOTO_URL,
        onDismissRequest = {},
        imageDescription = "Movie Image"
    )
}