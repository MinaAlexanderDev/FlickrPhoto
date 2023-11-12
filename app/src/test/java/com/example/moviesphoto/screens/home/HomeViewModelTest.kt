package com.example.moviesphoto.screens.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.example.moviesphoto.MainCoroutineRule
import com.example.moviesphoto.data.domain.FakeMoviesPagerUseCaseTest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val testCoroutineDispatcher = StandardTestDispatcher()
    private val testCoroutineScope = TestScope(testCoroutineDispatcher)

    private var useCase = FakeMoviesPagerUseCaseTest()
    private var viewModel = HomeViewModel(useCase, testCoroutineDispatcher)


    @Before
    fun setUp() {
        Dispatchers.setMain(testCoroutineDispatcher)
    }

    @After
    fun teardown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should load movies`() = testCoroutineScope.runTest {
        useCase.setNullable(false)
        viewModel.moviesPhotoList.test {
            val item = awaitItem()
            Assert.assertNotNull(item)
        }
    }

    @Test
    fun `should load movies empty`() = testCoroutineScope.runTest {
        useCase.setNullable(true)
        viewModel.moviesPhotoList.test {
            val item = awaitItem()
            Assert.assertNotNull(item)
        }
    }
}


