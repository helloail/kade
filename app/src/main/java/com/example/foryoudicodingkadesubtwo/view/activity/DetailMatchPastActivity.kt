package com.example.foryoudicodingkadesubtwo.view.activity

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.foryoudicodingkadesubtwo.helper.ApiRepository
import com.example.foryoudicodingkadesubtwo.view.model.FavoriteEntity
import com.example.foryoudicodingkadesubtwo.ImageAway.ImageAwayPresenter
import com.example.foryoudicodingkadesubtwo.ImageAway.ImageAwayView
import com.example.foryoudicodingkadesubtwo.ImageHome.ImageHomePresenter
import com.example.foryoudicodingkadesubtwo.ImageHome.ImageHomeView
import com.example.foryoudicodingkadesubtwo.R
import com.example.foryoudicodingkadesubtwo.R.menu.detail_menu
import com.example.foryoudicodingkadesubtwo.helper.database
import com.example.foryoudicodingkadesubtwo.view.model.ImageAwayInit
import com.example.foryoudicodingkadesubtwo.view.model.ImageHomeInit
import com.example.foryoudicodingkadesubtwo.view.model.PastMatchInit
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast

class DetailMatchPastActivity : AppCompatActivity(), ImageHomeView, ImageAwayView {


    private lateinit var presenter: ImageHomePresenter
    private lateinit var presenteraway: ImageAwayPresenter

    private lateinit var data: PastMatchInit
    private lateinit var id: String

    private var isFavorite: Boolean = false
    private var menuItem: Menu? = null
    val request = ApiRepository()
    val gson = Gson()

    companion object {
        const val SET_PARCELABLE = "extra_person"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        initToolbar()

        data = intent.getParcelableExtra(SET_PARCELABLE) as PastMatchInit

        Log.d("jsondata", data.toString())
        id = data.idEvent
        home_team.text = data.strHomeTeam
        away_team.text = data.strAwayTeam
        scoreHome.text = data.intHomeScore
        scoreAway.text = data.intAwayScore
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

        presentHome()
        presentAway()
    }

    fun presentHome() {

        presenter = ImageHomePresenter(this, request, gson)

        presenter.getImageHome(data.idHomeTeam)
    }


    fun presentAway() {
        presenteraway = ImageAwayPresenter(this, request, gson)

        presenteraway.getAwayImage(data.idAwayTeam)

    }

    override fun showLoading() {
//        progressBar.visible()
    }

    override fun hideLoading() {
//        progressBar.invisible()
    }

    override fun showTeamList(data: List<ImageHomeInit>) {
        val team = data.get(0)

        team.strTeamBadge.let { Picasso.get().load(it).into(image_home) }

    }

    override fun showImageAway(data: List<ImageAwayInit>) {
        val team = data.get(0)

        team.strTeamBadge.let { Picasso.get().load(it).into(image_away) }

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
                    FavoriteEntity.HOME_SCORE to data.intHomeScore,
                    FavoriteEntity.AWAY_SCORE to data.intAwayScore,
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
                    FavoriteEntity.YELLOW_HOME to data.strHomeYellowCards,
                    FavoriteEntity.YELLOW_AWAY to data.strHomeYellowCards

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