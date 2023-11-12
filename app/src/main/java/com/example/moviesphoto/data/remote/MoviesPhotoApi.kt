package com.example.moviesphoto.data.remote


import com.example.moviephoto.BuildConfig.API_KEY
import com.example.moviesphoto.data.model.MoviePhoto
import com.example.moviesphoto.util.Constants.PAGE_SIZE
import com.example.moviesphoto.util.Constants.STARTING_PAGE_INDEX
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesPhotoApi {
    @GET("rest/")
    suspend fun getMoviePhoto(
        @Query(METHOD) method: String? = FLICKER_PHOTO_SEARCH,
        @Query(API_KEY_QUERY) apiKey: String = API_KEY,
        @Query(FORMAT) format: String? = JSON,
        @Query(NO_JSON_CALL_BAC) noJsonCallBacK: Int? = NO_JSON_CALL_BACK_DATA,
        @Query(PER_PAGE) perPage: Int = PAGE_SIZE,
        @Query(PAGE) page: Int = STARTING_PAGE_INDEX,
        @Query(TEXT) text: String? = TEXT_DATA,
    ): Response<MoviePhoto>

    companion object {
        const val FLICKER_PHOTO_SEARCH = "flickr.photos.search"
        const val METHOD = "method"
        const val FORMAT = "format"
        const val NO_JSON_CALL_BAC = "nojsoncallback"
        const val TEXT = "text"
        const val PAGE = "page"
        const val PER_PAGE = "per_page"
        const val API_KEY_QUERY = "api_key"
        const val JSON = "json"
        const val TEXT_DATA = "Color"
        const val NO_JSON_CALL_BACK_DATA = 1
    }


}