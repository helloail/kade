package com.example.foryoudicodingkadesubtwo.view.activity

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.foryoudicodingkadesubtwo.FavoriteEntity
import com.example.foryoudicodingkadesubtwo.R
import com.example.foryoudicodingkadesubtwo.database
import com.example.foryoudicodingkadesubtwo.view.model.ImageAwayInit
import com.example.foryoudicodingkadesubtwo.view.model.ImageHomeInit
import com.example.foryoudicodingkadesubtwo.viewmodel.ImageAwayViewModel
import com.example.foryoudicodingkadesubtwo.viewmodel.ImageHomeViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast

class DetailFavoritesActivity : AppCompatActivity() {

    private lateinit var data: FavoriteEntity
    private var isFavorite: Boolean = false
    private var menuItem: Menu? = null
    private lateinit var id: String
    private lateinit var hViewModel: ImageHomeViewModel

    private lateinit var aViewModel: ImageAwayViewModel

    companion object {
        const val SET_PARCELABLE = "extra_person"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        initToolbar()
        data = intent.getParcelableExtra(SET_PARCELABLE) as FavoriteEntity
        id = data.matchId
        home_team.text = data.homeTeamName
        away_team.text = data.awayTeamName
        scoreHome.text = data.homeScore
        scoreAway.text = data.awayScore
        title_time.text = data.matchDate + " " + data.matchTime
        Goalhome.text = data.Goalhome
        Goalaway.text = data.Goalaway
        GkHome.text = data.GkHome
        GkAway.text = data.GkAway
        DeffHome.text = data.DeffHome
        DeffAway.text = data.DeffAway
        forwardHome.text = data.forwardHome
        forwardAway.text = data.forwardAway
        MidHome.text = data.MidHome
        MidAway.text = data.MidAway
        subHome.text = data.subHome
        subAway.text = data.subAway
        redHome.text = data.redHome
        redAway.text = data.redAway
        yellowHome.text = data.yellowHome
        yellowAway.text = data.yellowAway

        favoriteState()

        hViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            ImageHomeViewModel::class.java
        )
        hViewModel.setListmovie(data.homeTeamId)
//        setListmovie(data.idleague)

        hViewModel.getListMovie().observe(this, Observer {
            it?.also {
                if (it == null) {
                    toast("Detail init null")
                }


                if (it != null) {

                    Log.d("jsondetail", it.toString())
                    ImageHome(it)


                }
            }
        })


        aViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            ImageAwayViewModel::class.java
        )
        aViewModel.setListmovie(data.awayTeamId)
//        setListmovie(data.idleague)

        aViewModel.getListMovie().observe(this, Observer {

            it?.also {
                if (it == null) {
                    toast("Detail init null")
                }


                if (it != null) {

                    Log.d("jsondetail", it.toString())
                    ImageAway(it)


                }
            }
        })

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
                    FavoriteEntity.FAVORITE_MATCH,
                    FavoriteEntity.MATCH_ID to data.matchId,
                    FavoriteEntity.HOME_TEAM_ID to data.homeTeamId,
                    FavoriteEntity.AWAY_TEAM_ID to data.awayTeamId,
                    FavoriteEntity.HOME_TEAM_NAME to data.homeTeamName,
                    FavoriteEntity.AWAY_TEAM_NAME to data.awayTeamName,
                    FavoriteEntity.HOME_SCORE to data.homeScore,
                    FavoriteEntity.AWAY_SCORE to data.awayScore,
                    FavoriteEntity.MATCH_DATE to data.matchDate,
                    FavoriteEntity.MATCH_TIME to data.matchTime,
                    FavoriteEntity.GOAL_HOME to data.Goalhome,
                    FavoriteEntity.GOAL_AWAY to data.Goalaway,
                    FavoriteEntity.GK_HOME to data.GkHome,
                    FavoriteEntity.GK_AWAY to data.GkAway,
                    FavoriteEntity.DEFF_HOME to data.DeffHome,
                    FavoriteEntity.DEFF_AWAY to data.DeffAway,
                    FavoriteEntity.FORWARD_HOME to data.forwardHome,
                    FavoriteEntity.FORWARD_AWAY to data.forwardAway,
                    FavoriteEntity.MID_HOME to data.MidHome,
                    FavoriteEntity.MID_AWAY to data.MidAway,
                    FavoriteEntity.SUB_HOME to data.subHome,
                    FavoriteEntity.SUB_AWAY to data.subAway,
                    FavoriteEntity.RED_HOME to data.redHome,
                    FavoriteEntity.RED_AWAY to data.redAway,
                    FavoriteEntity.YELLOW_HOME to data.yellowHome,
                    FavoriteEntity.YELLOW_AWAY to data.yellowAway



                )
            }


            toast("ditambahkan")

        } catch (e: SQLiteConstraintException) {
            toast(e.localizedMessage)
        }


    }

    private fun ImageHome(team: List<ImageHomeInit>) {
        val team = team?.get(0)

        team?.strTeamBadge.let { Picasso.get().load(it).into(image_home) }

    }

    private fun ImageAway(team: List<ImageAwayInit>) {
        val team = team?.get(0)

        team?.strTeamBadge.let { Picasso.get().load(it).into(image_away) }

    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(
                    FavoriteEntity.FAVORITE_MATCH,
                    "(${FavoriteEntity.MATCH_ID} = {matchId})",
                    "matchId" to id
                )
            }
            toast("dihapus")
        } catch (e: SQLiteConstraintException) {
        }
    }

    private fun favoriteState() {
        database.use {
            val result = select(FavoriteEntity.FAVORITE_MATCH)
                .whereArgs(
                    "(MATCH_ID = {id})",
                    "id" to id
                )
            val favorite = result.parseList(classParser<FavoriteEntity>())
            if (favorite.isNotEmpty()) {
                isFavorite = true
                toast("data, ${id}, sudah ada")
            }

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