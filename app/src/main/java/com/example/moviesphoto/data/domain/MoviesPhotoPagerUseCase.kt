package com.example.moviesphoto.data.domain

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.moviesphoto.data.dataSource.MoviesPhotoRemoteMediator
import com.example.moviesphoto.data.local.MoviesPhotoDatabase
import com.example.moviesphoto.data.model.Photo
import com.example.moviesphoto.data.remote.MoviesPhotoApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesPhotoPagerUseCase @Inject constructor(
    private val moviePhotoDb: MoviesPhotoDatabase,
    private val moviesPhotoApi: MoviesPhotoApi
) : IMoviesPhotoPagerUseCase {


    @OptIn(ExperimentalPagingApi::class)
    override fun loadMoviesPhotoPage(): Flow<PagingData<Photo>> {
        return Pager(config = PagingConfig(pageSize = 10),
            remoteMediator = MoviesPhotoRemoteMediator(
                moviesPhotoDb = moviePhotoDb,
                moviesPhotoApi = moviesPhotoApi
            ),
            pagingSourceFactory = {
                moviePhotoDb.getMoviesPhotoDao().moviesPhotoPagingSource()
            }).flow
    }
}