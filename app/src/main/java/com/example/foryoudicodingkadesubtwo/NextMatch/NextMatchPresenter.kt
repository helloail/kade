package com.example.foryoudicodingkadesubtwo.NextMatch

import com.example.foryoudicodingkadesubtwo.helper.ApiRepository
import com.example.foryoudicodingkadesubtwo.helper.CoroutineContextProvider
import com.example.foryoudicodingkadesubtwo.helper.DBApi
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NextMatchPresenter (private val view: NextView,
                          private val apiRepository: ApiRepository,
                          private val gson: Gson, private val contextPool: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getNextMatchList(teamId: String?) {
        view.showLoading()

        GlobalScope.launch(contextPool.main){
            val data = gson.fromJson(apiRepository
                .doRequestAsync(DBApi.getNextMatch(teamId)).await(),
                NextMatchRespoonse::class.java
            )
            view.showTeamList(data.events)
            view.hideLoading()
        }
    }

}