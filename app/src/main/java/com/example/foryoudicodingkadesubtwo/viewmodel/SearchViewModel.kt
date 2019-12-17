package com.example.foryoudicodingkadesubtwo.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.example.foryoudicodingkadesubtwo.view.model.SearchActivityInit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONException
import org.json.JSONObject


class SearchViewModel : ViewModel() {


    private val mObserveList = MutableLiveData<ArrayList<SearchActivityInit>>()

    internal fun setSearchaMatch(id: String?) {
        val listItems = ArrayList<SearchActivityInit>()

        AndroidNetworking.get("https://www.thesportsdb.com/api/v1/json/1/searchevents.php?e=" + id)
            .setTag("test")
            .setPriority(Priority.LOW)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    // do anything with response
                    Log.d("jsonsearch", response.toString())

                    try {
                        val jsonArrayData = response.getJSONArray("event")
                        for (i in 0 until jsonArrayData.length()) {
                            val jsonObjectData = jsonArrayData.getJSONObject(i)
                            val id = jsonObjectData.getString("strSport")
                            val liga = jsonObjectData.getString("idLeague")
                            if (id == "Soccer" ) {
                                val data = Gson().fromJson<SearchActivityInit>(
                                    jsonObjectData.toString(),
                                    object : TypeToken<SearchActivityInit>() {}.type
                                )
                                listItems.add(data)
                            }

                        }
                        mObserveList.postValue(listItems)
                    } catch (e: JSONException) {

                    }
                }

                override fun onError(error: ANError) {
                    // handle error
                }
            })
    }

    fun getSearchMatch(): LiveData<ArrayList<SearchActivityInit>> {
        return mObserveList
    }
}