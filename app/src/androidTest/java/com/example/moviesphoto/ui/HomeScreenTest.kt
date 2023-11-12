package com.example.moviesphoto.ui

import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeUp
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.moviesphoto.MainActivity
import com.example.moviesphoto.commons.ui.MoviesListLazyColumn
import com.example.moviesphoto.data.domain.FakeMoviesPagerUseCaseTest
import com.example.moviesphoto.data.model.Photo
import com.example.moviesphoto.screens.home.HomeViewModel
import com.example.moviesphoto.ui.theme.IMOVIESTheme
import com.example.moviesphoto.util.Constants.TASK_LIST_TEST_TAG
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(
    AndroidJUnit4::class
)
class HomeScreenTest {

    private lateinit var useCase: FakeMoviesPagerUseCaseTest
    private lateinit var viewModel: HomeViewModel

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun before() {
        val testCoroutineDispatcher = StandardTestDispatcher()

        useCase = FakeMoviesPagerUseCaseTest()
        viewModel =
            HomeViewModel(photoPagerUseCase = useCase, ioDispatcher = testCoroutineDispatcher)
    }

    @Test
    fun testTaskListScreenIfTasksAreEmpty() {

        val _moviesList = MutableStateFlow(PagingData.empty<Photo>())
        val moviesList: StateFlow<PagingData<Photo>> = _moviesList
        useCase.setNullable(true)

        runBlocking {
            useCase.loadMoviesPhotoPage().collect {
                _moviesList.value = it
            }

        }
        composeTestRule.activity.setContent {
            IMOVIESTheme {
                MoviesListLazyColumn(
                    moviesPhotoPaging = moviesList.collectAsLazyPagingItems(),
                    onClick = {},
                    imageDescription = "",
                    moviesPhotoState = viewModel.state.collectAsState(),
                    isMoviePhotoDialogVisible = {},

                    )
            }
        }
        composeTestRule.onNodeWithTag(TASK_LIST_TEST_TAG)
            .onChildren()
            .assertCountEquals(0)
    }

    @Test
    fun testTaskListScreenIfTasksAreNotEmpty() {
        val _moviesList = MutableStateFlow(PagingData.empty<Photo>())
        val moviesList: StateFlow<PagingData<Photo>> = _moviesList
        useCase.setNullable(false)

        runBlocking {
            useCase.loadMoviesPhotoPage().collect {
                _moviesList.value = it
            }
        }
        composeTestRule.activity.setContent {
            IMOVIESTheme {
                MoviesListLazyColumn(
                    moviesPhotoPaging = moviesList.collectAsLazyPagingItems(),
                    onClick = {},
                    imageDescription = "",
                    moviesPhotoState = viewModel.state.collectAsState(),
                    isMoviePhotoDialogVisible = {},

                    )
            }
        }
        composeTestRule.onNodeWithTag(TASK_LIST_TEST_TAG).performTouchInput { swipeUp() }
        composeTestRule.onNodeWithTag(TASK_LIST_TEST_TAG).onChildren()
            .assertCountEquals(5)

    }

    @Test
    fun testTaskListScreenIfTasksAreFullImageSize() {
        val _moviesList = MutableStateFlow(PagingData.empty<Photo>())
        val moviesList: StateFlow<PagingData<Photo>> = _moviesList
        useCase.setNullable(false)

        runBlocking {
            useCase.loadMoviesPhotoPage().collect {
                _moviesList.value = it
            }
        }

        composeTestRule.activity.setContent {
            IMOVIESTheme {
                MoviesListLazyColumn(
                    moviesPhotoPaging = moviesList.collectAsLazyPagingItems(),
                    onClick = {},
                    imageDescription = "",
                    moviesPhotoState = viewModel.state.collectAsState(),
                    isMoviePhotoDialogVisible = {},

                    )
            }
        }
        composeTestRule.onNodeWithTag(TASK_LIST_TEST_TAG).onChildren().onFirst()
            .performClick()

        composeTestRule.onAllNodesWithTag(TASK_LIST_TEST_TAG).assertCountEquals(1)

    }

}
