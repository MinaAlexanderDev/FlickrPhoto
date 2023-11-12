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
            farm = 66,
            id = "53324023052",
            isfamily = 0,
            isfriend = 0,
            ispublic = 0,
            owner = "199140610",
            secret = "168f6d77de",
            server = "65535",
            title = "vertical sun mural color",
            rowId = 1
        ),
        Photo(
            farm = 66,
            id = "53325339760",
            isfamily = 0,
            isfriend = 0,
            ispublic = 0,
            owner = "48923613@N03",
            secret = "d278348e3f",
            server = "65535",
            title = "faire des bonds!!_1078",
            rowId = 2
        ),
        Photo(
            farm = 66,
            id = "53324400948",
            isfamily = 0,
            isfriend = 0,
            ispublic = 0,
            owner = "27415612@N04",
            secret = "f762872511",
            server = "65535",
            title = "",
            rowId = 3
        ),
        Photo(
            farm = 66,
            id = "53324023052",
            isfamily = 0,
            isfriend = 0,
            ispublic = 0,
            owner = "199140610",
            secret = "168f6d77de",
            server = "65535",
            title = "vertical sun mural color",
            rowId = 4
        ),
        Photo(
            farm = 66,
            id = "53325339760",
            isfamily = 0,
            isfriend = 0,
            ispublic = 0,
            owner = "48923613@N03",
            secret = "d278348e3f",
            server = "65535",
            title = "faire des bonds!!_1078",
            rowId = 5
        ),
        Photo(
            farm = 66,
            id = "53324400948",
            isfamily = 0,
            isfriend = 0,
            ispublic = 0,
            owner = "27415612@N04",
            secret = "f762872511",
            server = "65535",
            title = "",
            rowId = 6
        ),
        Photo(
            farm = 66,
            id = "53324400948",
            isfamily = 0,
            isfriend = 0,
            ispublic = 0,
            owner = "27415612@N04",
            secret = "f762872511",
            server = "65535",
            title = "",
            rowId = 7
        ),
        Photo(
            farm = 66,
            id = "53324400948",
            isfamily = 0,
            isfriend = 0,
            ispublic = 0,
            owner = "27415612@N04",
            secret = "f762872511",
            server = "65535",
            title = "",
            rowId = 8
        )

    )
    private val pagingData = PagingData.from(moviesPhotoList)
    private val pagingDataNull: PagingData<Photo> = PagingData.from(emptyList())
    private var isNull: Boolean = false


    fun setNullable(isNull: Boolean) {
        this.isNull = isNull
    }

    override fun loadMoviesPhotoPage(): Flow<PagingData<Photo>> {

        if (!isNull) {

            return { pagingData }.asFlow()
        } else {

            return { pagingDataNull }.asFlow()
        }
    }
}
