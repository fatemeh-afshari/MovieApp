package com.example.videoapp

import com.example.videoapp.data.api.API
import com.example.videoapp.data.model.ApiResponse
import com.example.videoapp.data.model.Movie
import com.example.videoapp.data.model.MovieDetail
import retrofit2.Response

class MockAPI : API {
    override suspend fun getMovieList(apiKey: String, page: Int): Response<ApiResponse> {
        return Response.success(
            ApiResponse(
                results = listOf(
                    Movie(id = 1),
                    Movie(id = 2),
                ),
                page = 1,
                totalPages = 10, totalResults = 100

            )
        )


    }

    override suspend fun getMovieDetail(movieId: String, apiKey: String): Response<MovieDetail> {
        return Response.success(MovieDetail())
    }
}