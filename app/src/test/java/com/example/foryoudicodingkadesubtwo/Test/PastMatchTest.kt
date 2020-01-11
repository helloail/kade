package com.example.foryoudicodingkadesubtwo.Test

import com.example.foryoudicodingkadesubtwo.PastMatch.PastMatchPresenter
import com.example.foryoudicodingkadesubtwo.PastMatch.PastMatchResponse
import com.example.foryoudicodingkadesubtwo.PastMatch.PastMatchView
import com.example.foryoudicodingkadesubtwo.helper.ApiRepository
import com.example.foryoudicodingkadesubtwo.TestContextProvider
import com.example.foryoudicodingkadesubtwo.view.model.PastMatchInit
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class PastMatchTest {
    @Mock
    private lateinit var view: PastMatchView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResponse: Deferred<String>

    private lateinit var presenter: PastMatchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = PastMatchPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetTeamList() {
        val teams: MutableList<PastMatchInit> = mutableListOf()
        val response = PastMatchResponse(teams)
        val league = "4332"

        runBlocking {
            Mockito.`when`(apiRepository.doRequestAsync(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)
            Mockito.`when`(apiResponse.await()).thenReturn("")
            Mockito.`when`(
                gson.fromJson(
                    "",
                    PastMatchResponse::class.java
                )
            ).thenReturn(response)

            presenter.getPastMatchList(league)
            Mockito.verify(view).showLoading()
            Mockito.verify(view).showTeamList(teams)
            Mockito.verify(view).hideLoading()
        }
    }

}