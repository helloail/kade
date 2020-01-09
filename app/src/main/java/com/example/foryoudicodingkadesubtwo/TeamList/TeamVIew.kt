package com.example.foryoudicodingkadesubtwo.TeamList

import com.example.foryoudicodingkadesubtwo.view.model.TeamListInit

interface TeamVIew {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<TeamListInit>)
}