package com.example.foryoudicodingkadesubtwo.Search

import com.example.foryoudicodingkadesubtwo.view.model.SearchActivityInit

interface SearchMatchVIew {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<SearchActivityInit>)
}