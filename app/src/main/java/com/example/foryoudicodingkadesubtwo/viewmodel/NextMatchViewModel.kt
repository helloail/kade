package com.example.foryoudicodingkadesubtwo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.example.foryoudicodingkadesubtwo.view.model.NextMatchInit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONException
import org.json.JSONObject

class NextMatchViewModel : ViewModel() {


    private val mObserveList = MutableLiveData<ArrayList<NextMatchInit>>()

    internal fun setListNextMatch(id: String?) {
        val listItems = ArrayList<NextMatchInit>()

        AndroidNetworking.get("https://www.thesportsdb.com/api/v1/json/1/eventsnextleague.php?id="+id)
            .setTag("test")
            .setPriority(Priority.LOW)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    // do anything with response

                    try {
                        val jsonArrayData = response.getJSONArray("events")
                        for (i in 0 until jsonArrayData.length()) {
                            val jsonObjectData = jsonArrayData.getJSONObject(i)
                            val data = Gson().fromJson<NextMatchInit>(
                                jsonObjectData.toString(),
                                object : TypeToken<NextMatchInit>() {}.type
                            )
                            listItems.add(data)
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

    fun getListNextMatch(): LiveData<ArrayList<NextMatchInit>> {
        return mObserveList
    }
}