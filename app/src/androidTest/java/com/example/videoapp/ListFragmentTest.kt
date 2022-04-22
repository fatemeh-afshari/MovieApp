package com.example.videoapp

import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.paging.PagingData
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.MediumTest
import com.example.videoapp.data.model.Movie
import com.example.videoapp.presentation.adapter.MovieListAdapter
import com.example.videoapp.presentation.view.ListFragment
import com.example.videoapp.presentation.view.ListFragmentDirections
import com.example.videoapp.presentation.view.MainFragmentFactory
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import javax.inject.Inject

@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class ListFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var fragmentFactory : MainFragmentFactory

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
     fun testNavigationFromArtToArtDetails() {
        val navController = Mockito.mock(NavController::class.java)

        launchFragmentInHiltContainer<ListFragment>(
            factory = fragmentFactory
        ) {
            Navigation.setViewNavController(requireView(),navController)

            adapter.submitData(this.lifecycle, PagingData.from(listOf(Movie())))

        }


        Espresso.onView(ViewMatchers.withId(R.id.movieList)).perform(
            RecyclerViewActions.actionOnItemAtPosition<MovieListAdapter.MovieViewHolder>(
                0,click()
            )

        )

        Mockito.verify(navController).navigate(
            ListFragmentDirections.actionDetailFragment()
        )
    }
}