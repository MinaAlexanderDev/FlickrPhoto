package com.example.moviesphoto.commons.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.moviephoto.R
import com.example.moviesphoto.util.Constants
import com.example.moviesphoto.util.Constants.TASK_Item_LIST_TEST_TAG


@Composable
fun MovieItem(
    moviePhotoUrl: String,
    onClick: () -> Unit,
    imageDescription: String,

    ) {
    Card(modifier = Modifier.testTag(TASK_Item_LIST_TEST_TAG)) {
        Column {
            Image(
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current).data(data = moviePhotoUrl)
                        .apply(block = fun ImageRequest.Builder.() {
                            placeholder(R.drawable.ic_placeholder)
                            crossfade(true)
                        }).build()
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clickable { onClick.invoke() },
                contentScale = ContentScale.Crop,
                contentDescription = imageDescription
            )

        }

    }
}

@Preview
@Composable
fun MovieItemPreview() {
    MovieItem(
        onClick = { },
        moviePhotoUrl = Constants.ADS_PHOTO_URL, imageDescription = "",
    )

}