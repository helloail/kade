package com.example.foryoudicodingkadesubtwo.Test

import com.example.foryoudicodingkadesubtwo.helper.ApiRepository
import com.example.foryoudicodingkadesubtwo.NextMatch.NextMatchPresenter
import com.example.foryoudicodingkadesubtwo.NextMatch.NextMatchRespoonse
import com.example.foryoudicodingkadesubtwo.NextMatch.NextView
import com.example.foryoudicodingkadesubtwo.TestContextProvider
import com.example.foryoudicodingkadesubtwo.view.model.NextMatchInit
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.*

class NextMatchTest {
    @Mock
    private lateinit var view: NextView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResponse: Deferred<String>

    private lateinit var presenter: NextMatchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = NextMatchPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetTeamList() {
        val teams: MutableList<NextMatchInit> = mutableListOf()
        val response = NextMatchRespoonse(teams)
        val league = "4332"

        runBlocking {
            Mockito.`when`(apiRepository.doRequestAsync(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)
            Mockito.`when`(apiResponse.await()).thenReturn("")
            Mockito.`when`(
                gson.fromJson(
                    "",
                    NextMatchRespoonse::class.java
                )
            ).thenReturn(response)

            presenter.getNextMatchList(league)
            Mockito.verify(view).showLoading()
            Mockito.verify(view).showTeamList(teams)
            Mockito.verify(view).hideLoading()
        }
    }
}