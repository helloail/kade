package com.example.foryoudicodingkadesubtwo.Test

import com.example.foryoudicodingkadesubtwo.helper.ApiRepository
import com.example.foryoudicodingkadesubtwo.ImageAway.ImageAwayPresenter
import com.example.foryoudicodingkadesubtwo.ImageAway.ImageAwayResponse
import com.example.foryoudicodingkadesubtwo.ImageAway.ImageAwayView
import com.example.foryoudicodingkadesubtwo.TestContextProvider
import com.example.foryoudicodingkadesubtwo.view.model.ImageAwayInit
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class ImageAwayTest {

    @Mock
    private lateinit var view: ImageAwayView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResponse: Deferred<String>

    private lateinit var presenter: ImageAwayPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = ImageAwayPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetTeamList() {
        val teams: MutableList<ImageAwayInit> = mutableListOf()
        val response = ImageAwayResponse(teams)
        val league = "134634"

        runBlocking {
            Mockito.`when`(apiRepository.doRequestAsync(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)
            Mockito.`when`(apiResponse.await()).thenReturn("")
            Mockito.`when`(
                gson.fromJson(
                    "",
                    ImageAwayResponse::class.java
                )
            ).thenReturn(response)

            presenter.getAwayImage(league)
            Mockito.verify(view).showLoading()
            Mockito.verify(view).showImageAway(teams)
            Mockito.verify(view).hideLoading()
        }
    }
}