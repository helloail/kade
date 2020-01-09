package com.example.foryoudicodingkadesubtwo.SearchMatch

import com.example.foryoudicodingkadesubtwo.view.model.TeamListInit

interface SearchTeamVIew {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<TeamListInit>)
}