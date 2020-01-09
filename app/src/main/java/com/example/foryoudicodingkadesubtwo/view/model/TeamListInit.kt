package com.example.foryoudicodingkadesubtwo.view.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TeamListInit(


    @SerializedName("idTeam")
    var idTeam: String? = null,

    @SerializedName("strTeam")
    var strTeam: String? = null,

    @SerializedName("strStadium")
    var strStadium: String? = null,

    @SerializedName("intFormedYear")
    var intFormedYear: String? = null,

    @SerializedName("strSport")
    var strSport: String? = null,

    @SerializedName("strTeamBadge")
    var strTeamBadge: String? = null,

    @SerializedName("strDescriptionEN")
    var strDescriptionEN: String? = null


) : Parcelable