package com.example.videoapp.data.api

import com.example.videoapp.data.model.ApiResponse
import com.example.videoapp.data.model.MovieDetail
import com.example.videoapp.util.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface API {
    @GET("popular/")
    suspend fun getMovieList(
        @Query("api_key") apiKey : String = API_KEY,
        @Query("page") page: Int
    ) : Response<ApiResponse>

    @GET("{movieId}")
    suspend fun getMovieDetail(
        @Path("movieId") movieId: String,
        @Query("api_key") apiKey : String = API_KEY
    ) : Response<MovieDetail>
}
