package com.example.foryoudicodingkadesubtwo.view.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class DetailLeagueInit(
    val strLeague: String?,
    val strGender: String?,
    val strCountry: String?,
    val strBadge: String
) : Parcelable