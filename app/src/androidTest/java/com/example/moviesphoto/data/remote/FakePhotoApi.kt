package com.example.moviesphoto.data.remote


import com.example.moviesphoto.data.model.MoviePhoto
import com.example.moviesphoto.data.model.Photo
import com.example.moviesphoto.data.model.Photos
import okhttp3.Protocol
import okhttp3.Request
import retrofit2.Response

class FakePhotoApi : MoviesPhotoApi {
    private val moviesPhotoList = listOf(
        Photo(
            farm = 0,
            id = "",
            isfamily = 0,
            isfriend = 0,
            ispublic = 0,
            owner = "",
            secret = "",
            server = "",
            title = "",
            rowId = 1
        ),
        Photo(
            farm = 0,
            id = "",
            isfamily = 0,
            isfriend = 0,
            ispublic = 0,
            owner = "",
            secret = "",
            server = "",
            title = "",
            rowId = 2
        )

    )
    private val photos =
        Photos(page = 1, pages = 20, perpage = 20, photo = moviesPhotoList, total = 1000)

    private val photosEmpty =
        Photos(page = 1, pages = 20, perpage = 20, photo = emptyList(), total = 1000)

    private val successMoviesPhotoResponse = MoviePhoto(photos = photos, stat = "")
    private val successMoviesPhotoResponseEmpty = MoviePhoto(photos = photosEmpty, stat = "")
    private var isNull: Boolean = false
    fun setGetPopularMovies(isNull: Boolean) {
        this.isNull = isNull
    }

    override suspend fun getMoviePhoto(
        method: String?,
        apiKey: String,
        format: String?,
        noJsonCallBacK: Int?,
        perPage: Int,
        page: Int,
        text: String?
    ): Response<MoviePhoto> {
        return if (!isNull) {
            Response.success(
                successMoviesPhotoResponse,
                okhttp3.Response.Builder()
                    .code(200)
                    .message("Response.success()")
                    .protocol(Protocol.HTTP_1_1)
                    .request(Request.Builder().url("http://test-url/").build())
                    .receivedResponseAtMillis(1619053449513)
                    .sentRequestAtMillis(1619053443814)
                    .build()
            )

        } else {
            Response.success(
                successMoviesPhotoResponseEmpty,
                okhttp3.Response.Builder()
                    .code(200)
                    .message("Response.success()")
                    .protocol(Protocol.HTTP_1_1)
                    .request(Request.Builder().url("http://test-url/").build())
                    .receivedResponseAtMillis(1619053449513)
                    .sentRequestAtMillis(1619053443814)
                    .build()
            )
        }
    }
}