package com.example.foryoudicodingkadesubtwo.Test

import com.example.foryoudicodingkadesubtwo.helper.ApiRepository
import com.example.foryoudicodingkadesubtwo.SearchMatch.SearchMatchPresenter
import com.example.foryoudicodingkadesubtwo.SearchMatch.SearchMatchVIew
import com.example.foryoudicodingkadesubtwo.SearchMatch.SearchTeamResponse
import com.example.foryoudicodingkadesubtwo.SearchMatch.SearchTeamVIew
import com.example.foryoudicodingkadesubtwo.TestContextProvider
import com.example.foryoudicodingkadesubtwo.view.model.SearchActivityInit
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class SearchMatchTest {
    @Mock
    private lateinit var view: SearchMatchVIew

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResponse: Deferred<String>

    private lateinit var presenter: SearchMatchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = SearchMatchPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetTeamList() {
        val teams: MutableList<SearchActivityInit> = mutableListOf()
        val response = SearchTeamResponse(teams)
        val league = "man"

        runBlocking {
            Mockito.`when`(apiRepository.doRequestAsync(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)
            Mockito.`when`(apiResponse.await()).thenReturn("")
            Mockito.`when`(
                gson.fromJson(
                    "",
                    SearchTeamResponse::class.java
                )
            ).thenReturn(response)

            presenter.getSearchMatch(league)
            Mockito.verify(view).showLoading()
            Mockito.verify(view).showTeamList(teams)
            Mockito.verify(view).hideLoading()
        }
    }

}