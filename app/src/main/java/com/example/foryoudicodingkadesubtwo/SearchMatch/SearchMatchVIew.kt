package com.example.foryoudicodingkadesubtwo.SearchMatch

import com.example.foryoudicodingkadesubtwo.view.model.SearchActivityInit

interface SearchMatchVIew {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<SearchActivityInit>)
}