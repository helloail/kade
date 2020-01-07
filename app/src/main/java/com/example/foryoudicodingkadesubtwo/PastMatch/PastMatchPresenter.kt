package com.example.foryoudicodingkadesubtwo.PastMatch

import com.example.foryoudicodingkadesubtwo.helper.ApiRepository
import com.example.foryoudicodingkadesubtwo.helper.CoroutineContextProvider
import com.example.foryoudicodingkadesubtwo.helper.DBApi
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PastMatchPresenter (private val view: PastMatchView,
                          private val apiRepository: ApiRepository,
                          private val gson: Gson, private val contextPool: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getPastMatchList(teamId: String?) {
        view.showLoading()

        GlobalScope.launch(contextPool.main){
            val data = gson.fromJson(apiRepository
                .doRequestAsync(DBApi.getPastMatch(teamId)).await(),
                PastMatchResponse::class.java
            )
            view.showTeamList(data.events)
            view.hideLoading()
        }
    }

}