package com.example.foryoudicodingkadesubtwo.Test

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.foryoudicodingkadesubtwo.mock
import com.example.foryoudicodingkadesubtwo.view.model.ImageAwayInit
import com.example.foryoudicodingkadesubtwo.view.model.ImageHomeInit
import com.example.foryoudicodingkadesubtwo.viewmodel.ImageAwayViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mockito

class ImageAwayTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var imageAwayViewModel: ImageAwayViewModel

    private val observer: Observer<List<ImageAwayInit>> = mock()

    val id = "134634"

    @Before
    fun before() {
        imageAwayViewModel = ImageAwayViewModel()


    }

    @Test
    fun getLeagueDetailSuccsess() {

        val expectedUser = ImageHomeInit(
            "Dutch Eredivisie"
        )

        imageAwayViewModel.setListmovie(id)

        imageAwayViewModel.getListMovie().observeForever(observer)

        val captor = ArgumentCaptor.forClass(ImageAwayInit::class.java)

        captor.run {
            Mockito.verify(observer, Mockito.times(1)).onChanged(listOf(capture()))
            Assert.assertEquals(expectedUser, value)
        }
    }
}