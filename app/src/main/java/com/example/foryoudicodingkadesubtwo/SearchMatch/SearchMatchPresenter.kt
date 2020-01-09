package com.example.foryoudicodingkadesubtwo.SearchMatch

import com.example.foryoudicodingkadesubtwo.helper.ApiRepository
import com.example.foryoudicodingkadesubtwo.helper.CoroutineContextProvider
import com.example.foryoudicodingkadesubtwo.helper.DBApi
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchMatchPresenter(
    private val view: SearchMatchVIew,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val contextPool: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getSearchMatch(teamId: String?) {
        view.showLoading()

        GlobalScope.launch(contextPool.main) {
            val data = gson.fromJson(
                apiRepository
                    .doRequestAsync(DBApi.getSearch(teamId)).await(),
                SearchMatchResponse::class.java
            )
            val value = data.event.filter { it.strSport=="Soccer" }
            view.showTeamList(value)
            view.hideLoading()
        }
    }

}