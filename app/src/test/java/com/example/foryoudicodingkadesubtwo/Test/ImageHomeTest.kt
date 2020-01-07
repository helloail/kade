package com.example.foryoudicodingkadesubtwo.Test

import com.example.foryoudicodingkadesubtwo.helper.ApiRepository
import com.example.foryoudicodingkadesubtwo.ImageHome.ImageHomePresenter
import com.example.foryoudicodingkadesubtwo.ImageHome.ImageHomeResponse
import com.example.foryoudicodingkadesubtwo.ImageHome.ImageHomeView
import com.example.foryoudicodingkadesubtwo.TestContextProvider
import com.example.foryoudicodingkadesubtwo.view.model.ImageHomeInit
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class ImageHomeTest {

    @Mock
    private lateinit var view: ImageHomeView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResponse: Deferred<String>

    private lateinit var presenter: ImageHomePresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = ImageHomePresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetTeamList() {
        val teams: MutableList<ImageHomeInit> = mutableListOf()
        val response = ImageHomeResponse(teams)
        val league = "134634"

        runBlocking {
            Mockito.`when`(apiRepository.doRequestAsync(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)
            Mockito.`when`(apiResponse.await()).thenReturn("")
            Mockito.`when`(
                gson.fromJson(
                    "",
                    ImageHomeResponse::class.java
                )
            ).thenReturn(response)

            presenter.getImageHome(league)
            Mockito.verify(view).showLoading()
            Mockito.verify(view).showTeamList(teams)
            Mockito.verify(view).hideLoading()
        }
    }
}