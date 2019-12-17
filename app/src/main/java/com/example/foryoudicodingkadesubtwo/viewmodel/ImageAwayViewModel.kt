package com.example.foryoudicodingkadesubtwo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.example.foryoudicodingkadesubtwo.view.model.DetailLeagueInit
import com.example.foryoudicodingkadesubtwo.view.model.ImageAwayInit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONException
import org.json.JSONObject

class ImageAwayViewModel : ViewModel() {


    private val mObserveListLeague = MutableLiveData<List<ImageAwayInit>>()

    internal fun setListmovie(id: String?) {
        val listItems = ArrayList<ImageAwayInit>()

        AndroidNetworking.get("https://www.thesportsdb.com/api/v1/json/1/lookupteam.php?id="+id)
            .setTag("test")
            .setPriority(Priority.LOW)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    // do anything with response

                    try {
                        val jsonArrayData = response.getJSONArray("teams")
                        for (i in 0 until jsonArrayData.length()) {
                            val jsonObjectData = jsonArrayData.getJSONObject(i)
                            val data = Gson().fromJson<ImageAwayInit>(
                                jsonObjectData.toString(),
                                object : TypeToken<ImageAwayInit>() {}.type
                            )
                            listItems.add(data)
                        }
                        mObserveListLeague.postValue(listItems)
                    } catch (e: JSONException) {

                    }


                }

                override fun onError(error: ANError) {
                    // handle error
                }
            })


    }

    fun getListMovie(): LiveData<List<ImageAwayInit>> {
        return mObserveListLeague
    }
}