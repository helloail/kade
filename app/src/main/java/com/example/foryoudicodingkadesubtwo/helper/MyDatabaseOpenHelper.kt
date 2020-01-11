package com.example.foryoudicodingkadesubtwo.helper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.foryoudicodingkadesubtwo.view.model.MatchFavoriteEntity
import com.example.foryoudicodingkadesubtwo.view.model.TeamFavoriteEntity
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(ctx: Context) :
    ManagedSQLiteOpenHelper(ctx, "FavoriteTeam.db", null, 1) {
    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance =
                    MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {

        db.createTable(
            MatchFavoriteEntity.FAVORITE_MATCH, true,
            MatchFavoriteEntity.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            MatchFavoriteEntity.MATCH_ID to TEXT + UNIQUE,
            MatchFavoriteEntity.HOME_TEAM_ID to TEXT,
            MatchFavoriteEntity.AWAY_TEAM_ID to TEXT,
            MatchFavoriteEntity.HOME_TEAM_NAME to TEXT,
            MatchFavoriteEntity.AWAY_TEAM_NAME to TEXT,
            MatchFavoriteEntity.HOME_SCORE to TEXT,
            MatchFavoriteEntity.AWAY_SCORE to TEXT,
            MatchFavoriteEntity.MATCH_DATE to TEXT,
            MatchFavoriteEntity.MATCH_TIME to TEXT,
            MatchFavoriteEntity.GOAL_HOME to TEXT,
            MatchFavoriteEntity.GOAL_AWAY to TEXT,
            MatchFavoriteEntity.GK_HOME to TEXT,
            MatchFavoriteEntity.GK_AWAY to TEXT,
            MatchFavoriteEntity.DEFF_HOME to TEXT,
            MatchFavoriteEntity.DEFF_AWAY to TEXT,
            MatchFavoriteEntity.FORWARD_HOME to TEXT,
            MatchFavoriteEntity.FORWARD_AWAY to TEXT,
            MatchFavoriteEntity.MID_HOME to TEXT,
            MatchFavoriteEntity.MID_AWAY to TEXT,
            MatchFavoriteEntity.SUB_HOME to TEXT,
            MatchFavoriteEntity.SUB_AWAY to TEXT,
            MatchFavoriteEntity.RED_HOME to TEXT,
            MatchFavoriteEntity.RED_AWAY to TEXT,
            MatchFavoriteEntity.YELLOW_AWAY to TEXT,
            MatchFavoriteEntity.YELLOW_HOME to TEXT
        )


        db.createTable(
            TeamFavoriteEntity.FAVORITE_TEAM, true,
            TeamFavoriteEntity.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            TeamFavoriteEntity.IDTEAM to TEXT + UNIQUE,
            TeamFavoriteEntity.strTeam to TEXT,
            TeamFavoriteEntity.strStadium to TEXT,
            TeamFavoriteEntity.strCountry to TEXT,
            TeamFavoriteEntity.intFormedYear to TEXT,
            TeamFavoriteEntity.strSport to TEXT,
            TeamFavoriteEntity.strTeamBadge to TEXT,
            TeamFavoriteEntity.strLeague to TEXT,
            TeamFavoriteEntity.strAlternate to TEXT,
            TeamFavoriteEntity.strKeywords to TEXT,
            TeamFavoriteEntity.strTeamFanart4 to TEXT,
            TeamFavoriteEntity.strDescriptionEN to TEXT

        )
        // Here you create tables

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Here you can upgrade tables, as usual
        db.dropTable((MatchFavoriteEntity.FAVORITE_MATCH), true)
        db.dropTable((TeamFavoriteEntity.FAVORITE_TEAM), true)
    }
}

val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(
        applicationContext
    )