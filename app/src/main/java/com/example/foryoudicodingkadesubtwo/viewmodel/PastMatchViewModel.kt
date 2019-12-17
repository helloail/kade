package com.example.foryoudicodingkadesubtwo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.example.foryoudicodingkadesubtwo.view.model.PastMatchInit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONException
import org.json.JSONObject

class PastMatchViewModel : ViewModel() {


    private val mObserveList = MutableLiveData<ArrayList<PastMatchInit>>()

    internal fun setListPastMatch(id: String?) {
        val listItems = ArrayList<PastMatchInit>()

        AndroidNetworking.get("https://www.thesportsdb.com/api/v1/json/1/eventspastleague.php?id="+id)
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
                            val data = Gson().fromJson<PastMatchInit>(
                                jsonObjectData.toString(),
                                object : TypeToken<PastMatchInit>() {}.type
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

    fun getListPastMatch(): LiveData<ArrayList<PastMatchInit>> {
        return mObserveList
    }
}