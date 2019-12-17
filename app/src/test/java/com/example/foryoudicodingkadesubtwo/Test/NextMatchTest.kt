package com.example.foryoudicodingkadesubtwo.Test

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.foryoudicodingkadesubtwo.mock
import com.example.foryoudicodingkadesubtwo.view.model.NextMatchInit
import com.example.foryoudicodingkadesubtwo.viewmodel.NextMatchViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mockito

class NextMatchTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var nextMatchViewModel: NextMatchViewModel

    private val observer: Observer<List<NextMatchInit>> = mock()

    val id = "4337"

    @Before
    fun before() {
        nextMatchViewModel = NextMatchViewModel()

        nextMatchViewModel.getListNextMatch().observeForever(observer)
    }

    @Test
    fun getLeagueDetailSuccsess() {

        val expectedUser = NextMatchInit("Dutch Eredivisie",
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
            "")

        nextMatchViewModel.setListNextMatch(id)

        val captor = ArgumentCaptor.forClass(NextMatchInit::class.java)

        captor.run {
            Mockito.verify(observer, Mockito.times(1)).onChanged(listOf(capture()))
            Assert.assertEquals(expectedUser, value)
        }
    }
}