package com.example.foryoudicodingkadesubtwo.ImageHome

import com.example.foryoudicodingkadesubtwo.view.model.ImageHomeInit
import com.example.foryoudicodingkadesubtwo.view.model.PastMatchInit

interface ImageHomeView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<ImageHomeInit>)
}