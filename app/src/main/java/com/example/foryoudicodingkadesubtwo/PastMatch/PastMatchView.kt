package com.example.foryoudicodingkadesubtwo.PastMatch

import com.example.foryoudicodingkadesubtwo.view.model.PastMatchInit


interface PastMatchView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<PastMatchInit>)

}