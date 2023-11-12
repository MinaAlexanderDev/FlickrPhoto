package com.example.moviesphoto.data.data_source

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import app.cash.turbine.test
import com.example.moviesphoto.data.domain.MoviesPhotoPagerUseCase
import com.example.moviesphoto.data.local.MoviesPhotoDatabase
import com.example.moviesphoto.data.remote.FakePhotoApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MoviesPhotoRemoteMediatorTest {

    private var moviesApi = FakePhotoApi()
    private lateinit var database: MoviesPhotoDatabase
    private lateinit var useCase: MoviesPhotoPagerUseCase

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(), MoviesPhotoDatabase::class.java
        ).allowMainThreadQueries().build()

        useCase = MoviesPhotoPagerUseCase(database, moviesApi)
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun `load_refresh_movies`() {
        runTest {
            // Arrange
            moviesApi.setGetPopularMovies(false)
            useCase.loadMoviesPhotoPage().test {
                Assert.assertNotNull(awaitItem())
            }
        }
    }

    @Test
    fun `load_refresh_movies_with_empty_list`() {
        runTest {
            // Arrange
            moviesApi.setGetPopularMovies(true)
            useCase.loadMoviesPhotoPage().test {
                Assert.assertNotNull(awaitItem())
            }
        }
    }

}


