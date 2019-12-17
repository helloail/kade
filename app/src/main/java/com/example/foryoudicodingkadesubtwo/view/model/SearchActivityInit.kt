package com.example.foryoudicodingkadesubtwo.view.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchActivityInit(
    val strHomeTeam: String?,
    val strAwayTeam: String?,
    val intRound: String?,
    val dateEvent: String?,
    val strTimeLocal: String?,
    val idEvent: String,
    val idHomeTeam: String?,
    val idAwayTeam: String?,
    val intAwayScore: String?,
    val intHomeScore: String?,
    val strHomeGoalDetails: String?,
    val strHomeRedCards: String?,
    val strHomeYellowCards: String?,
    val strHomeLineupGoalkeeper: String?,
    val strHomeLineupDefense: String?,
    val strHomeLineupMidfield: String?,
    val strHomeLineupForward: String?,
    val strHomeLineupSubstitutes: String?,
    val strAwayRedCards: String?,
    val strAwayYellowCards: String?,
    val strAwayGoalDetails: String?,
    val strAwayLineupGoalkeeper: String?,
    val strAwayLineupDefense: String?,
    val strAwayLineupMidfield: String?,
    val strAwayLineupForward: String?,
    val strAwayLineupSubstitutes: String?
) : Parcelable

