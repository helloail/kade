package com.example.foryoudicodingkadesubtwo.Test

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.foryoudicodingkadesubtwo.mock
import com.example.foryoudicodingkadesubtwo.view.model.ImageHomeInit
import com.example.foryoudicodingkadesubtwo.viewmodel.ImageHomeViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mockito

class ImageHomeTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var imageHomeViewModel: ImageHomeViewModel

    private val observer: Observer<List<ImageHomeInit>> = mock()

    val id = "134634"

    @Before
    fun before() {
        imageHomeViewModel = ImageHomeViewModel()

        imageHomeViewModel.getListMovie().observeForever(observer)
    }

    @Test
    fun getLeagueDetailSuccsess() {

        val expectedUser = ImageHomeInit(
            "Dutch Eredivisie"
        )

        imageHomeViewModel.setListmovie(id)

        val captor = ArgumentCaptor.forClass(ImageHomeInit::class.java)

        captor.run {
            Mockito.verify(observer, Mockito.times(1)).onChanged(listOf(capture()))
            Assert.assertEquals(expectedUser, value)
        }
    }
}