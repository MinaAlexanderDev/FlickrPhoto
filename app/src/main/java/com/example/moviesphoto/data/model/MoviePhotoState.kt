package com.example.moviesphoto.data.model

data class MoviePhotoState(
    val singleMoviePhotoUrl: String? = null,
    val isMoviePhotoDialogVisible: Boolean = false
)

sealed class MoviePhotoStateEvent {
    data object LoadMoviesPhotoEvent : MoviePhotoStateEvent()
    data class SingleMoviePhotoEvent(val moviePhotoUrl: String) : MoviePhotoStateEvent()
    data object IsMoviePhotoDialogVisible : MoviePhotoStateEvent()
}