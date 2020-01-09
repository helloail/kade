package com.example.foryoudicodingkadesubtwo.SearchMatch

import com.example.foryoudicodingkadesubtwo.helper.ApiRepository
import com.example.foryoudicodingkadesubtwo.helper.CoroutineContextProvider
import com.example.foryoudicodingkadesubtwo.helper.DBApi
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchTeamPresenter(
    private val view: SearchTeamVIew,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val contextPool: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getSearchTeam(teamId: String?) {
        view.showLoading()

        GlobalScope.launch(contextPool.main) {
            val data = gson.fromJson(
                apiRepository
                    .doRequestAsync(DBApi.getSearchTeam(teamId)).await(),
                SearchTeamResponse::class.java
            )
            val value = data.teams.filter { it.strSport=="Soccer" }
            view.showTeamList(value)
            view.hideLoading()
        }
    }

}