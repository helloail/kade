package com.example.foryoudicodingkadesubtwo.Test

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.foryoudicodingkadesubtwo.mock
import com.example.foryoudicodingkadesubtwo.view.model.SearchActivityInit
import com.example.foryoudicodingkadesubtwo.viewmodel.SearchViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mockito

class SearchMatchTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var searchViewModel: SearchViewModel

    private val observer: Observer<List<SearchActivityInit>> = mock()

    val id = "man"

    @Before
    fun before() {
        searchViewModel = SearchViewModel()

        searchViewModel.getSearchMatch().observeForever(observer)
    }

    @Test
    fun getLeagueDetailSuccsess() {

        val expectedUser = SearchActivityInit(
            "Dutch Eredivisie",
            "Male", "Holland",
            "https://www.thesportsdb.com/images/media/league/badge/ywoi5k1534590331.png",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
        )

        searchViewModel.setSearchaMatch(id)

        val captor = ArgumentCaptor.forClass(SearchActivityInit::class.java)

        captor.run {
            Mockito.verify(observer, Mockito.times(1)).onChanged(listOf(capture()))
            Assert.assertEquals(expectedUser, value)
        }
    }

}