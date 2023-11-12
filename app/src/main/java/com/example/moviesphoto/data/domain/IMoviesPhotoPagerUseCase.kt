package com.example.moviesphoto.data.domain

import androidx.paging.PagingData
import com.example.moviesphoto.data.model.Photo
import kotlinx.coroutines.flow.Flow

interface IMoviesPhotoPagerUseCase {
    fun loadMoviesPhotoPage(): Flow<PagingData<Photo>>
}