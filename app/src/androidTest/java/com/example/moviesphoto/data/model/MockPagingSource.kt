package com.example.moviesphoto.data.model

import androidx.paging.PagingSource
import androidx.paging.PagingState

class MockPagingSource(private val movieList: List<Photo>) : PagingSource<Int, Photo>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        return LoadResult.Page(
            data = movieList, prevKey = null, nextKey = 1
        )
    }

    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? {
        return 1
    }
}