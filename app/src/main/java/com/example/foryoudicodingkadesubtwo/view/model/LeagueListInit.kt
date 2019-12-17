package com.example.foryoudicodingkadesubtwo.view.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class LeagueListInit(
    val idleague: String?,
    val name: String?,
    val negara: String?,
    val firstleague: String?,
    val logo: Int?
) : Parcelable