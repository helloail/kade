package com.example.foryoudicodingkadesubtwo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FavoriteEntity(
    val id: Long?,
    val matchId: String,
    val homeTeamId: String?,
    val awayTeamId: String?,
    val homeTeamName: String?,
    val awayTeamName: String?,
    val homeScore: String?,
    val awayScore: String?,
    val matchDate: String?,
    val matchTime: String?,
    val Goalhome: String?,
    val Goalaway: String?,
    val GkHome: String?,
    val GkAway: String?,
    val DeffHome: String?,
    val DeffAway: String?,
    val forwardHome: String?,
    val forwardAway: String?,
    val MidHome: String?,
    val MidAway: String?,
    val subHome: String?,
    val subAway: String?,
    val redHome: String?,
    val redAway: String?,
    val yellowHome: String?,
    val yellowAway: String?
) : Parcelable {

    companion object {
        const val FAVORITE_MATCH: String = "FAVORITE_MATCH"
        const val ID: String = "ID_"
        const val MATCH_ID: String = "MATCH_ID"
        const val HOME_TEAM_ID: String = "HOME_TEAM_ID"
        const val AWAY_TEAM_ID: String = "AWAY_TEAM_ID"
        const val HOME_TEAM_NAME: String = "HOME_TEAM_NAME"
        const val AWAY_TEAM_NAME: String = "AWAY_TEAM_NAME"
        const val HOME_SCORE: String = "HOME_SCORE"
        const val AWAY_SCORE: String = "AWAY_SCORE"
        const val MATCH_DATE: String = "MATCH_DATE"
        const val MATCH_TIME: String = "MATCH_TIME"
        const val GOAL_HOME: String = "GOAL_HOME"
        const val GOAL_AWAY: String = "GOAL_AWAY"
        const val GK_HOME: String = "GK_HOME"
        const val GK_AWAY: String = "GK_AWAY"
        const val DEFF_HOME: String = "DEFF_HOME"
        const val DEFF_AWAY: String = "DEFF_AWAY"
        const val FORWARD_HOME: String = "FORWARD_HOME"
        const val FORWARD_AWAY: String = "FORWARD_AWAY"
        const val MID_AWAY: String = "MID_AWAY"
        const val MID_HOME: String = "MID_HOME"
        const val SUB_HOME: String = "SUB_HOME"
        const val SUB_AWAY: String = "SUB_AWAY"
        const val RED_HOME: String = "RED_HOME"
        const val RED_AWAY: String = "RED_AWAY"
        const val YELLOW_HOME: String = "YELLOW_HOME"
        const val YELLOW_AWAY: String = "YELLOW_AWAY"
    }
}
