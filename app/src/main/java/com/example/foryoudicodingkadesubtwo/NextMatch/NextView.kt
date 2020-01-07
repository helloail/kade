package com.example.foryoudicodingkadesubtwo.NextMatch

import com.example.foryoudicodingkadesubtwo.view.model.NextMatchInit

interface NextView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<NextMatchInit>)
}