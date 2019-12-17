package com.example.foryoudicodingkadesubtwo.Test


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.foryoudicodingkadesubtwo.mock
import com.example.foryoudicodingkadesubtwo.view.model.DetailLeagueInit
import com.example.foryoudicodingkadesubtwo.viewmodel.DetailLeagueViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mockito.times
import org.mockito.Mockito.verify

class DetailMatchTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var detailLeagueViewModel: DetailLeagueViewModel

    private val observer: Observer<List<DetailLeagueInit>> = mock()



    val id = "4337"


    @Before
    fun before() {

        detailLeagueViewModel = DetailLeagueViewModel()

    }

    @Test
    fun getLeagueDetailSuccsess() {

        val expectedUser = DetailLeagueInit(
            "Dutch Eredivisie",
            "Male",
            "Holland",
            "https://www.thesportsdb.com/images/media/league/badge/ywoi5k1534590331.png"
        )
        detailLeagueViewModel.setListmovie(id)

         val captor = ArgumentCaptor.forClass(DetailLeagueInit::class.java)

        captor?.run {
            verify(observer, times(1)).onChanged(listOf(capture()))
            assertEquals(expectedUser, value)
        }
    }
}

