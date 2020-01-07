package com.example.foryoudicodingkadesubtwo.Test


import com.example.foryoudicodingkadesubtwo.helper.ApiRepository
import com.example.foryoudicodingkadesubtwo.DetailLeague.DetailLeaguePresenter
import com.example.foryoudicodingkadesubtwo.DetailLeague.DetailLeagueResponse
import com.example.foryoudicodingkadesubtwo.DetailLeague.DetailLeagueView
import com.example.foryoudicodingkadesubtwo.TestContextProvider
import com.example.foryoudicodingkadesubtwo.view.model.DetailLeagueInit
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class DetailMatchTest {
    @Mock
    private lateinit var view: DetailLeagueView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResponse: Deferred<String>

    private lateinit var presenter: DetailLeaguePresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = DetailLeaguePresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetTeamList() {
        val teams: MutableList<DetailLeagueInit> = mutableListOf()
        val response = DetailLeagueResponse(teams)
        val league = "4332"

        runBlocking {
            Mockito.`when`(apiRepository.doRequestAsync(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)
            Mockito.`when`(apiResponse.await()).thenReturn("")
            Mockito.`when`(
                gson.fromJson(
                    "",
                    DetailLeagueResponse::class.java
                )
            ).thenReturn(response)

            presenter.getDetailLeague(league)
            verify(view).showLoading()
            verify(view).showTeamList(teams)
            verify(view).hideLoading()
        }
    }
}