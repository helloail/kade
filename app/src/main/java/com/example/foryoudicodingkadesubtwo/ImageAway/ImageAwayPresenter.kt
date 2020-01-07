package com.example.foryoudicodingkadesubtwo.ImageAway

import com.example.foryoudicodingkadesubtwo.helper.ApiRepository
import com.example.foryoudicodingkadesubtwo.helper.CoroutineContextProvider
import com.example.foryoudicodingkadesubtwo.helper.DBApi
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ImageAwayPresenter(
    private val view: ImageAwayView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val contextPool: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getAwayImage(teamId: String?) {
        view.showLoading()

        GlobalScope.launch(contextPool.main) {
            val data = gson.fromJson(
                apiRepository
                    .doRequestAsync(DBApi.getImage(teamId)).await(),
                ImageAwayResponse::class.java
            )
            view.showImageAway(data.teams)
            view.hideLoading()
        }
    }

}
