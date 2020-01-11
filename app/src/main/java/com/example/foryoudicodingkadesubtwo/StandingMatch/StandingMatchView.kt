package com.example.foryoudicodingkadesubtwo.PastMatch

import com.example.foryoudicodingkadesubtwo.view.model.StandingInit


interface StandingMatchView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<StandingInit>)

}