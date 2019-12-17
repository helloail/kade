package com.example.foryoudicodingkadesubtwo

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*
class MyDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteTeam.db", null, 1) {
    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {

        db.createTable(
            FavoriteEntity.FAVORITE_MATCH, true,
            FavoriteEntity.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            FavoriteEntity.MATCH_ID to TEXT + UNIQUE,
            FavoriteEntity.HOME_TEAM_ID to TEXT,
            FavoriteEntity.AWAY_TEAM_ID to TEXT,
            FavoriteEntity.HOME_TEAM_NAME to TEXT,
            FavoriteEntity.AWAY_TEAM_NAME to TEXT,
            FavoriteEntity.HOME_SCORE to TEXT,
            FavoriteEntity.AWAY_SCORE to TEXT,
            FavoriteEntity.MATCH_DATE to TEXT,
            FavoriteEntity.MATCH_TIME to TEXT,
            FavoriteEntity.GOAL_HOME to TEXT,
            FavoriteEntity.GOAL_AWAY to TEXT,
            FavoriteEntity.GK_HOME to TEXT,
            FavoriteEntity.GK_AWAY to TEXT,
            FavoriteEntity.DEFF_HOME to TEXT,
            FavoriteEntity.DEFF_AWAY to TEXT,
            FavoriteEntity.FORWARD_HOME to TEXT,
            FavoriteEntity.FORWARD_AWAY to TEXT,
            FavoriteEntity.MID_HOME to TEXT,
            FavoriteEntity.MID_AWAY to TEXT,
            FavoriteEntity.SUB_HOME to TEXT,
            FavoriteEntity.SUB_AWAY to TEXT,
            FavoriteEntity.RED_HOME to TEXT,
            FavoriteEntity.RED_AWAY to TEXT,
            FavoriteEntity.YELLOW_AWAY to TEXT,
            FavoriteEntity.YELLOW_HOME to TEXT
        )
        // Here you create tables

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Here you can upgrade tables, as usual
        db.dropTable((FavoriteEntity.FAVORITE_MATCH), true)
    }
}

val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)