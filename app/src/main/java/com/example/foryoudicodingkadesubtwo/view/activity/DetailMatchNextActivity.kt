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
import com.example.foryoudicodingkadesubtwo.R
import com.example.foryoudicodingkadesubtwo.R.id.add_to_favorite
import com.example.foryoudicodingkadesubtwo.R.menu.detail_menu
import com.example.foryoudicodingkadesubtwo.database
import com.example.foryoudicodingkadesubtwo.view.model.ImageAwayInit
import com.example.foryoudicodingkadesubtwo.view.model.ImageHomeInit
import com.example.foryoudicodingkadesubtwo.view.model.NextMatchInit
import com.example.foryoudicodingkadesubtwo.FavoriteEntity
import com.example.foryoudicodingkadesubtwo.viewmodel.ImageAwayViewModel
import com.example.foryoudicodingkadesubtwo.viewmodel.ImageHomeViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast

class DetailMatchNextActivity : AppCompatActivity() {

    private lateinit var hViewModel: ImageHomeViewModel

    private lateinit var aViewModel: ImageAwayViewModel
    private lateinit var data: NextMatchInit
    private lateinit var id: String

    private var isFavorite: Boolean = false
    private var menuItem: Menu? = null

    companion object {
        const val SET_PARCELABLE = "extra_person"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        initToolbar()

        data = intent.getParcelableExtra(DetailMatchPastActivity.SET_PARCELABLE) as NextMatchInit
        id = data.idEvent
        home_team.text = data.strHomeTeam
        away_team.text = data.strAwayTeam
        scoreHome.text = " "
        scoreAway.text = " "
        title_time.text = data.dateEvent + " " + data.strTimeLocal
        Goalhome.text = data.strHomeGoalDetails
        Goalaway.text = data.strAwayGoalDetails
        GkHome.text = data.strHomeLineupGoalkeeper
        GkAway.text = data.strAwayLineupGoalkeeper
        DeffHome.text = data.strHomeLineupDefense
        DeffAway.text = data.strAwayLineupDefense
        forwardHome.text = data.strHomeLineupForward
        forwardAway.text = data.strAwayLineupForward
        MidHome.text = data.strHomeLineupMidfield
        MidAway.text = data.strAwayLineupMidfield
        subHome.text = data.strHomeLineupSubstitutes
        subAway.text = data.strAwayLineupSubstitutes
        redHome.text = data.strHomeRedCards
        redAway.text = data.strAwayRedCards
        yellowHome.text = data.strHomeYellowCards
        yellowAway.text = data.strAwayRedCards

        favoriteState()

        hViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            ImageHomeViewModel::class.java
        )
        hViewModel.setListmovie(data.idHomeTeam)
//        setListmovie(data.idleague)

        hViewModel.getListMovie().observe(this, Observer {
            it?.also {
                if (it == null) {
                    toast("Detail init null")
                }

                if (it.size <= 0) {
                    toast("Hello, ${it.size}, Opened")
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
        aViewModel.setListmovie(data.idAwayTeam)
//        setListmovie(data.idleague)

        aViewModel.getListMovie().observe(this, Observer {
            it?.also {
                if (it == null) {
                    toast("Detail init null")
                }

                if (it.size <= 0) {
                    toast("Hello, ${it.size}, Opened")
                }
                if (it != null) {

                    Log.d("jsondetail", it.toString())
                    ImageAway(it)


                }
            }
        })


    }

    private fun ImageHome(team: List<ImageHomeInit>) {
        val team = team?.get(0)

        team?.strTeamBadge.let { Picasso.get().load(it).into(image_home) }

    }

    private fun ImageAway(team: List<ImageAwayInit>) {
        val team = team?.get(0)

        team?.strTeamBadge.let { Picasso.get().load(it).into(image_away) }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(detail_menu, menu)
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
                    FavoriteEntity.MATCH_ID to data.idEvent,
                    FavoriteEntity.HOME_TEAM_ID to data.idHomeTeam,
                    FavoriteEntity.AWAY_TEAM_ID to data.idAwayTeam,
                    FavoriteEntity.HOME_TEAM_NAME to data.strHomeTeam,
                    FavoriteEntity.AWAY_TEAM_NAME to data.strAwayTeam,
                    FavoriteEntity.HOME_SCORE to " ",
                    FavoriteEntity.AWAY_SCORE to " ",
                    FavoriteEntity.MATCH_DATE to data.dateEvent,
                    FavoriteEntity.MATCH_TIME to data.strTimeLocal,
                    FavoriteEntity.GOAL_HOME to data.strHomeGoalDetails,
                    FavoriteEntity.GOAL_AWAY to data.strAwayGoalDetails,
                    FavoriteEntity.GK_HOME to data.strHomeLineupGoalkeeper,
                    FavoriteEntity.GK_AWAY to data.strAwayLineupGoalkeeper,
                    FavoriteEntity.DEFF_HOME to data.strHomeLineupDefense,
                    FavoriteEntity.DEFF_AWAY to data.strAwayLineupDefense,
                    FavoriteEntity.FORWARD_HOME to data.strHomeLineupForward,
                    FavoriteEntity.FORWARD_AWAY to data.strAwayLineupForward,
                    FavoriteEntity.MID_HOME to data.strHomeLineupMidfield,
                    FavoriteEntity.MID_AWAY to data.strAwayLineupMidfield,
                    FavoriteEntity.SUB_HOME to data.strHomeLineupSubstitutes,
                    FavoriteEntity.SUB_AWAY to data.strAwayLineupSubstitutes,
                    FavoriteEntity.RED_HOME to data.strHomeRedCards,
                    FavoriteEntity.RED_AWAY to data.strAwayRedCards,
                    FavoriteEntity.YELLOW_HOME to data.dateEvent,
                    FavoriteEntity.YELLOW_AWAY to data.strTimeLocal


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
                    FavoriteEntity.FAVORITE_MATCH,
                    "(${FavoriteEntity.MATCH_ID} = {matchId})",
                    "matchId" to id
                )
            }
            toast("dihapus")
        } catch (e: SQLiteConstraintException) {
        }
    }

    private fun favoriteState(){
        database.use {
            val result = select(FavoriteEntity.FAVORITE_MATCH)
                .whereArgs("(MATCH_ID = {id})",
                    "id" to id
                )
            val favorite = result.parseList(classParser<FavoriteEntity>())
            if (favorite.isNotEmpty()) isFavorite = true
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            add_to_favorite -> {
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