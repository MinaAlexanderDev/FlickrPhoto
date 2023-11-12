package com.example.moviesphoto.data.local


import androidx.paging.PagingSource
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import assertk.assertThat
import assertk.assertions.isEqualTo
import com.example.moviesphoto.data.model.MockPagingSource
import com.example.moviesphoto.data.model.Photo
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MoviesPhotoDatabaseTest {
    private lateinit var database: MoviesPhotoDatabase
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

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(), MoviesPhotoDatabase::class.java
        ).allowMainThreadQueries().build()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun `insert_movies_and_rest_keys_get_first_movie_row_id_one`() = runTest {
        // Arrange
        val mockPagingSource = MockPagingSource(moviesPhotoList)
        val moviesPagingSource = mockPagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null, loadSize = 10, placeholdersEnabled = true
            )
        ) as? PagingSource.LoadResult.Page

        val movieDao = database.getMoviesPhotoDao()
        movieDao.insertAll(moviesPhotoList)
        movieDao.clearAll()
        movieDao.clearPrimaryKey()
        movieDao.insertAll(moviesPhotoList)
        // Act
        val result = movieDao.moviesPhotoPagingSource().load(
            PagingSource.LoadParams.Refresh(
                key = null, loadSize = 10, placeholdersEnabled = true
            )
        ) as? PagingSource.LoadResult.Page

        assertThat((moviesPagingSource)?.data?.get(0)?.rowId).isEqualTo(
            (result)?.data?.get(0)?.rowId
        )
    }

    @Test
    fun `insert_delete_and_get_empty_movie`() = runTest {
        // Arrange
        val movieDao = database.getMoviesPhotoDao()
        movieDao.insertAll(moviesPhotoList)
        movieDao.clearAll()

        // Act
        val result = movieDao.moviesPhotoPagingSource().load(
            PagingSource.LoadParams.Refresh(
                key = null, loadSize = 10, placeholdersEnabled = true
            )
        ) as? PagingSource.LoadResult.Page

        assertThat((result)?.data).isEqualTo(
            emptyList()
        )
    }

    @Test
    fun `insert_empty_and_get_empty_movies`() = runTest {
        // Arrange
        val mockPagingSource = MockPagingSource(emptyList())
        val moviesPagingSource = mockPagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null, loadSize = 10, placeholdersEnabled = true
            )
        ) as? PagingSource.LoadResult.Page

        val movieDao = database.getMoviesPhotoDao()
        movieDao.insertAll(emptyList())

        // Act
        val result = movieDao.moviesPhotoPagingSource().load(
            PagingSource.LoadParams.Refresh(
                key = null, loadSize = 10, placeholdersEnabled = true
            )
        ) as? PagingSource.LoadResult.Page


        assertThat((moviesPagingSource)?.data).isEqualTo(
            (result)?.data
        )
    }

    @Test
    fun `insert_and_get_movies`() = runTest {
        // Arrange
        val mockPagingSource = MockPagingSource(moviesPhotoList)
        val moviesPagingSource = mockPagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null, loadSize = 10, placeholdersEnabled = true
            )
        ) as? PagingSource.LoadResult.Page

        val movieDao = database.getMoviesPhotoDao()
        movieDao.insertAll(moviesPhotoList)

        // Act

        val result = movieDao.moviesPhotoPagingSource().load(
            PagingSource.LoadParams.Refresh(
                key = null, loadSize = 10, placeholdersEnabled = true
            )
        ) as? PagingSource.LoadResult.Page

        assertThat((moviesPagingSource)?.data).isEqualTo(
            (result)?.data
        )

    }
}


