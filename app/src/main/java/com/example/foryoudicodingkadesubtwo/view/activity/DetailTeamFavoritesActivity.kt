package com.example.foryoudicodingkadesubtwo.view.activity

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.foryoudicodingkadesubtwo.R
import com.example.foryoudicodingkadesubtwo.helper.database
import com.example.foryoudicodingkadesubtwo.view.model.TeamFavoriteEntity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detailteam.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast

class DetailTeamFavoritesActivity : AppCompatActivity() {

    private lateinit var data: TeamFavoriteEntity
    private lateinit var id: String

    private var isFavorite: Boolean = false
    private var menuItem: Menu? = null

    companion object {
        const val SET_PARCELABLE = "extra_person"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailteam)
        initComponent()
        initToolbar()
        favoriteState()
    }

    fun initComponent() {
        data = intent.getParcelableExtra(SET_PARCELABLE) as TeamFavoriteEntity
        id = data.idTeam
        titleteam.text = data.stTeam
        FormedYear.text = data.inFormedYear
        titlealternatife.text = data.stAlternate
        league.text = data.stLeague
        stadium.text = data.stStadium
        discription.text = data.stDescriptionEN
        keyword.text = data.stKeywords

        data.stTeamFanart4.let { Picasso.get().load(it).into(ìmagebanner) }
        data.stTeamBadge.let { Picasso.get().load(it).into(imagelogo) }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    fun initToolbar() {
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar!!.title = " "
        //set back button
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_heart_border)
    }

    private fun addToFavorite() {
        try {
            database.use {
                insert(
                    TeamFavoriteEntity.FAVORITE_TEAM,
                    TeamFavoriteEntity.IDTEAM to data.idTeam,
                    TeamFavoriteEntity.strTeam to data.stTeam,
                    TeamFavoriteEntity.strStadium to data.stStadium,
                    TeamFavoriteEntity.strCountry to data.stCountry,
                    TeamFavoriteEntity.intFormedYear to data.inFormedYear,
                    TeamFavoriteEntity.strSport to data.stSport,
                    TeamFavoriteEntity.strTeamBadge to data.stTeamBadge,
                    TeamFavoriteEntity.strLeague to data.stLeague,
                    TeamFavoriteEntity.strAlternate to data.stAlternate,
                    TeamFavoriteEntity.strKeywords to data.stKeywords,
                    TeamFavoriteEntity.strTeamFanart4 to data.stTeamFanart4,
                    TeamFavoriteEntity.strDescriptionEN to data.stDescriptionEN
                )
            }


            toast("ditambahkan")

        } catch (e: SQLiteConstraintException) {
            toast(e.localizedMessage)
        }

    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(
                    TeamFavoriteEntity.FAVORITE_TEAM,
                    "(${TeamFavoriteEntity.IDTEAM} = {matchId})",
                    "matchId" to id
                )
            }
            toast("dihapus")
        } catch (e: SQLiteConstraintException) {
        }
    }

    private fun favoriteState() {
        database.use {
            val result = select(TeamFavoriteEntity.FAVORITE_TEAM)
                .whereArgs(
                    "(${TeamFavoriteEntity.IDTEAM} = {id})",
                    "id" to id
                )
            val favorite = result.parseList(classParser<TeamFavoriteEntity>())
            if (favorite.isNotEmpty()) isFavorite = true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favorite -> {
                if (isFavorite)
                    removeFromFavorite()
                else
                    addToFavorite()

                isFavorite = !isFavorite
                setFavorite()

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

}