package com.example.foryoudicodingkadesubtwo.ImageAway

import com.example.foryoudicodingkadesubtwo.view.model.ImageAwayInit
import com.example.foryoudicodingkadesubtwo.view.model.ImageHomeInit

interface ImageAwayView {
    fun showLoading()
    fun hideLoading()
    fun showImageAway(data: List<ImageAwayInit>)
}