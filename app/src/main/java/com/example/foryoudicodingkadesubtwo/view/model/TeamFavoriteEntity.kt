package com.example.foryoudicodingkadesubtwo.view.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TeamFavoriteEntity(
    val id: Long?,
    var idTeam: String,
    var stTeam: String,
    var stStadium: String?,
    var stCountry: String?,
    var inFormedYear: String?,
    var stSport: String?,
    var stTeamBadge: String?,
    var stLeague: String?,
    var stAlternate: String?,
    var stKeywords: String?,
    var stTeamFanart4: String?,
    var stDescriptionEN: String?

) : Parcelable {

    companion object {
        const val FAVORITE_TEAM: String = "FAVORITE_TEAM"
        const val ID: String = "ID_"

        const val IDTEAM: String = "IDTEAM"

        const val strTeam: String = "strTeam"

        const val strStadium: String = "strStadium"

        const val strCountry: String = "strCountry"

        const val intFormedYear: String = "intFormedYear"

        const val strSport: String = "strSport"

        const val strTeamBadge: String = "strTeamBadge"

        const val strLeague: String = "strLeague"

        const val strAlternate: String = "strAlternate"

        const val strKeywords: String =  "strKeywords"

        const val strTeamFanart4: String = "strTeamFanart4"

        const val strDescriptionEN: String = "strDescriptionEN"
    }
}