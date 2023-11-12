package com.example.moviesphoto.data.dataSource

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.moviesphoto.data.local.MoviesPhotoDatabase
import com.example.moviesphoto.data.model.Photo
import com.example.moviesphoto.data.remote.MoviesPhotoApi
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class MoviesPhotoRemoteMediator(
    private val moviesPhotoApi: MoviesPhotoApi,
    private val moviesPhotoDb: MoviesPhotoDatabase,
) : RemoteMediator<Int, Photo>() {
    override suspend fun load(
        loadType: LoadType, state: PagingState<Int, Photo>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )

                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        1
                    } else {
                        (lastItem.rowId / state.config.pageSize) + 1
                    }
                }
            }

            val movies = moviesPhotoApi.getMoviePhoto(page = loadKey).body()?.photos
            moviesPhotoDb.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    moviesPhotoDb.getMoviesPhotoDao().clearAll()
                    moviesPhotoDb.getMoviesPhotoDao().clearPrimaryKey()
                }
                if (movies != null) {
                    moviesPhotoDb.getMoviesPhotoDao().insertAll(movies.photo)
                }
            }

            MediatorResult.Success(
                endOfPaginationReached = movies?.photo?.isEmpty() == true
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}