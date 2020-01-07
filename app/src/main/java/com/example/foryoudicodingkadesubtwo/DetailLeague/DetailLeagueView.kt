package com.example.foryoudicodingkadesubtwo.DetailLeague

import com.example.foryoudicodingkadesubtwo.view.model.DetailLeagueInit

interface DetailLeagueView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<DetailLeagueInit>)

}