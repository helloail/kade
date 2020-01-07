package com.example.foryoudicodingkadesubtwo.DetailLeague

import com.example.foryoudicodingkadesubtwo.helper.ApiRepository
import com.example.foryoudicodingkadesubtwo.helper.CoroutineContextProvider
import com.example.foryoudicodingkadesubtwo.helper.DBApi
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailLeaguePresenter(
    private val view: DetailLeagueView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val contextPool: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getDetailLeague(teamId: String?) {
        view.showLoading()

        GlobalScope.launch(contextPool.main) {
            val data = gson.fromJson(
                apiRepository
                    .doRequestAsync(DBApi.getDetailLeague(teamId)).await(),
                DetailLeagueResponse::class.java
            )
            view.showTeamList(data.leagues)
            view.hideLoading()
        }
    }

}