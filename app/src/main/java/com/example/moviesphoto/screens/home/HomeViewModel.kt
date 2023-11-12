package com.example.moviesphoto.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.moviesphoto.data.domain.IMoviesPhotoPagerUseCase
import com.example.moviesphoto.data.model.MoviePhotoState
import com.example.moviesphoto.data.model.MoviePhotoStateEvent
import com.example.moviesphoto.data.model.Photo
import com.example.moviesphoto.di.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val photoPagerUseCase: IMoviesPhotoPagerUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : ViewModel() {

    private val _moviesPhotoList = MutableStateFlow(PagingData.empty<Photo>())
    val moviesPhotoList: StateFlow<PagingData<Photo>> = _moviesPhotoList

    private val _moviesPhotoState = MutableStateFlow(MoviePhotoState())
    val state = _moviesPhotoState

    init {

        handleEvent(MoviePhotoStateEvent.LoadMoviesPhotoEvent)
    }

    fun handleEvent(event: MoviePhotoStateEvent) {
        when (event) {
            is MoviePhotoStateEvent.LoadMoviesPhotoEvent -> loadMoviesPhoto()
            is MoviePhotoStateEvent.SingleMoviePhotoEvent -> showSingleMoviesPhoto(moviePhotoUrl = event.moviePhotoUrl)
            is MoviePhotoStateEvent.IsMoviePhotoDialogVisible -> isMoviePhotoDialogVisible()
            else -> {}
        }
    }


    private fun loadMoviesPhoto() {
        viewModelScope.launch(ioDispatcher) {
            photoPagerUseCase.loadMoviesPhotoPage().cachedIn(this).collect { pagingMoviePhotoData ->
                _moviesPhotoList.value = pagingMoviePhotoData

            }
        }
    }

    private fun showSingleMoviesPhoto(moviePhotoUrl: String) {
        viewModelScope.launch(ioDispatcher) {
            _moviesPhotoState.update {
                it.copy(
                    singleMoviePhotoUrl = moviePhotoUrl,
                    isMoviePhotoDialogVisible = !_moviesPhotoState.value.isMoviePhotoDialogVisible
                )
            }
        }
    }

    private fun isMoviePhotoDialogVisible() {
        viewModelScope.launch(ioDispatcher) {
            _moviesPhotoState.update {
                it.copy(
                    isMoviePhotoDialogVisible = !_moviesPhotoState.value.isMoviePhotoDialogVisible
                )
            }
        }
    }
}