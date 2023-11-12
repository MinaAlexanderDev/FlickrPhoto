package com.example.moviesphoto.data.domain

import androidx.paging.PagingData
import com.example.moviesphoto.data.model.Photo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class FakeMoviesPagerUseCaseTest : IMoviesPhotoPagerUseCase {
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
            rowId = 0
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
            rowId = 0
        )

    )

    private val pagingData = PagingData.from(moviesPhotoList)
    private val pagingDataNull: PagingData<Photo> = PagingData.from(emptyList())
    private var isNull: Boolean = false


    fun setNullable(isNull: Boolean) {
        this.isNull = isNull
    }

    override fun loadMoviesPhotoPage(): Flow<PagingData<Photo>> {
        return if (!isNull) {
            { pagingData }.asFlow()
        } else {
            { pagingDataNull }.asFlow()
        }
    }
}
