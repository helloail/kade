package com.example.foryoudicodingkadesubtwo.Test

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.foryoudicodingkadesubtwo.mock
import com.example.foryoudicodingkadesubtwo.view.model.PastMatchInit
import com.example.foryoudicodingkadesubtwo.viewmodel.PastMatchViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mockito

class PastMatchTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var pastMatchViewModel: PastMatchViewModel

    private val observer: Observer<List<PastMatchInit>> = mock()

    val id = "4337"

    @Before
    fun before() {
        pastMatchViewModel = PastMatchViewModel()

        pastMatchViewModel.getListPastMatch().observeForever(observer)
    }

    @Test
    fun getLeagueDetailSuccsess() {

        val expectedUser = PastMatchInit("Dutch Eredivisie",
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
            "")

        pastMatchViewModel.setListPastMatch(id)

        val captor = ArgumentCaptor.forClass(PastMatchInit::class.java)

        captor.run {
            Mockito.verify(observer, Mockito.times(1)).onChanged(listOf(capture()))
            Assert.assertEquals(expectedUser, value)
        }
    }
}