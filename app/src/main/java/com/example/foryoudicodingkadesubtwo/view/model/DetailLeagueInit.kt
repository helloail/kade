package com.example.foryoudicodingkadesubtwo.view.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class DetailLeagueInit(


    @SerializedName("strLeague")
    var strLeague: String? = null,


    @SerializedName("strGender")
    var strGender: String? = null,

    @SerializedName("strCountry")
    var strCountry: String? = null,

    @SerializedName("strBadge")
    var strBadge: String? = null

) : Parcelable