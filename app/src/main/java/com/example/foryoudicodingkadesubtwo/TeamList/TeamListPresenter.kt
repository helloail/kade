package com.example.foryoudicodingkadesubtwo.TeamList

import com.example.foryoudicodingkadesubtwo.helper.ApiRepository
import com.example.foryoudicodingkadesubtwo.helper.CoroutineContextProvider
import com.example.foryoudicodingkadesubtwo.helper.DBApi
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeamListPresenter (private val view: TeamVIew,
                         private val apiRepository: ApiRepository,
                         private val gson: Gson, private val contextPool: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getTeamList(teamId: String?) {
        view.showLoading()

        GlobalScope.launch(contextPool.main){
            val data = gson.fromJson(apiRepository
                .doRequestAsync(DBApi.getListTeam(teamId)).await(),
                TeamListReponse::class.java
            )
            view.showTeamList(data.teams)
            view.hideLoading()
        }
    }

}