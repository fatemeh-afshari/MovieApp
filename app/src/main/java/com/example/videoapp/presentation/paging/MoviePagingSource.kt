package com.example.videoapp.presentation.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.videoapp.data.api.API
import com.example.videoapp.data.model.Movie
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject




 class MoviePagingSource @Inject constructor(
    val api: API
) : PagingSource<Int, Movie>() {


     private  val startingPageIndex = 1

    override suspend fun load(params: LoadParams<Int>):LoadResult<Int, Movie> {
        var pageIndex = params.key ?: startingPageIndex
        return try {
            val response = api.getMovieList(page = pageIndex)
            if(response!= null && response.isSuccessful){
                response.body()?.let {
            val nextKey =
                if (it.results.isEmpty()) {
                    null
                } else {
                     pageIndex +1
                }
            LoadResult.Page(
                data = it.results,
                prevKey = if (pageIndex == startingPageIndex) null else pageIndex,
                nextKey = nextKey
            )}?: LoadResult.Error(Exception())
            }else{
                return LoadResult.Error(NullPointerException())
            }
        }
        catch (exception: Exception) {
            return LoadResult.Error(exception)
        }catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {

        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}