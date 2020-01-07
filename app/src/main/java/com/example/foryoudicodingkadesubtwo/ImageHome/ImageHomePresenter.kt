package com.example.foryoudicodingkadesubtwo.ImageHome

import com.example.foryoudicodingkadesubtwo.helper.ApiRepository
import com.example.foryoudicodingkadesubtwo.helper.CoroutineContextProvider
import com.example.foryoudicodingkadesubtwo.helper.DBApi
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ImageHomePresenter(
    private val view: ImageHomeView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val contextPool: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getImageHome(teamId: String?) {
        view.showLoading()

        GlobalScope.launch(contextPool.main) {
            val data = gson.fromJson(
                apiRepository
                    .doRequestAsync(DBApi.getImage(teamId)).await(),
                ImageHomeResponse::class.java
            )
            view.showTeamList(data.teams)
            view.hideLoading()
        }
    }

}