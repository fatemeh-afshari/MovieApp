package com.example.videoapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingSource
import com.example.videoapp.data.api.API
import com.example.videoapp.data.model.ApiResponse
import com.example.videoapp.data.model.Movie
import com.example.videoapp.presentation.paging.MoviePagingSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test



@OptIn(ExperimentalCoroutinesApi::class)
class MoviePagingSourceTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutineTestRule = CoroutineTestRule()

    lateinit var api: API
    lateinit var pagingSource: MoviePagingSource

    companion object {
        val movieResponse = ApiResponse(
            results = listOf(
                Movie(id = 1),
                Movie(id = 2),
            ),
            page = 1,
            totalPages = 10, totalResults = 100

        )
    }
    @Before
    fun setup() {
        api = MockAPI()
        pagingSource = MoviePagingSource(api)
    }
    @Test
    fun `load returns page when on successful load`() = runBlockingTest {

        val expected =  PagingSource.LoadResult.Page(
            data = movieResponse.results,
            prevKey = null,
            nextKey = 2
        )
        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = 1,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )
        assertEquals(
           expected,
            result
        )
    }


}