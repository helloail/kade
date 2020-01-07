package com.example.foryoudicodingkadesubtwo.view.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class NextMatchInit (

    @SerializedName("strHomeTeam")
    var strHomeTeam: String? = null,

    @SerializedName("strAwayTeam")
    var strAwayTeam: String? = null,

    @SerializedName("intRound")
    var intRound: String? = null,

    @SerializedName("dateEvent")
    var dateEvent: String? = null,

    @SerializedName("strTimeLocal")
    var strTimeLocal: String? = null,

    @SerializedName("idHomeTeam")
    var idHomeTeam: String? = null,

    @SerializedName("idAwayTeam")
    var idAwayTeam: String? = null,

    @SerializedName("idEvent")
    var idEvent: String,

    @SerializedName("strHomeGoalDetails")
    var strHomeGoalDetails: String? = null,

    @SerializedName("strHomeRedCards")
    var strHomeRedCards: String? = null,

    @SerializedName("strHomeYellowCards")
    var strHomeYellowCards: String? = null,

    @SerializedName("strHomeLineupGoalkeeper")
    var strHomeLineupGoalkeeper: String? = null,

    @SerializedName("strHomeLineupDefense")
    var strHomeLineupDefense: String? = null,

    @SerializedName("strHomeLineupMidfield")
    var strHomeLineupMidfield: String? = null,

    @SerializedName("strHomeLineupForward")
    var strHomeLineupForward: String? = null,

    @SerializedName("strHomeLineupSubstitutes")
    var strHomeLineupSubstitutes: String? = null,

    @SerializedName("strAwayRedCards")
    var strAwayRedCards: String? = null,

    @SerializedName("strAwayYellowCards")
    var strAwayYellowCards: String? = null,

    @SerializedName("strAwayGoalDetails")
    var strAwayGoalDetails: String? = null,

    @SerializedName("strAwayLineupGoalkeeper")
    var strAwayLineupGoalkeeper: String? = null,

    @SerializedName("strAwayLineupDefense")
    var strAwayLineupDefense: String? = null,

    @SerializedName("strAwayLineupMidfield")
    var strAwayLineupMidfield: String? = null,

    @SerializedName("strAwayLineupForward")
    var strAwayLineupForward: String? = null,

    @SerializedName("strAwayLineupSubstitutes")
    var strAwayLineupSubstitutes: String? = null
) : Parcelable