package com.example.foryoudicodingkadesubtwo.helper

object DBApi {

    fun getDetailLeague(league: String?): String {
        return "https://www.thesportsdb.com/api/v1/json/1/lookupleague.php?id="+ league
    }
    fun getNextMatch(league: String?): String {
        return "https://www.thesportsdb.com/api/v1/json/1/eventsnextleague.php?id="+ league
    }

    fun getPastMatch(league: String?): String {
        return "https://www.thesportsdb.com/api/v1/json/1/eventspastleague.php?id="+ league
    }

    fun getImage(league: String?): String {
        return "https://www.thesportsdb.com/api/v1/json/1/lookupteam.php?id="+ league
    }

    fun getSearch(league: String?): String {
        return "https://www.thesportsdb.com/api/v1/json/1/searchevents.php?e="+ league}
}