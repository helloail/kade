package com.example.foryoudicodingkadesubtwo.view.activity

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.foryoudicodingkadesubtwo.helper.ApiRepository
import com.example.foryoudicodingkadesubtwo.view.model.MatchFavoriteEntity
import com.example.foryoudicodingkadesubtwo.ImageAway.ImageAwayPresenter
import com.example.foryoudicodingkadesubtwo.ImageAway.ImageAwayView
import com.example.foryoudicodingkadesubtwo.ImageHome.ImageHomePresenter
import com.example.foryoudicodingkadesubtwo.ImageHome.ImageHomeView
import com.example.foryoudicodingkadesubtwo.R
import com.example.foryoudicodingkadesubtwo.helper.database
import com.example.foryoudicodingkadesubtwo.view.model.ImageAwayInit
import com.example.foryoudicodingkadesubtwo.view.model.ImageHomeInit
import com.example.foryoudicodingkadesubtwo.view.model.SearchActivityInit
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detailmatch.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast


class DetailSearchActivity : AppCompatActivity(), ImageHomeView, ImageAwayView {


    private lateinit var presenter: ImageHomePresenter
    private lateinit var presenteraway: ImageAwayPresenter
    private lateinit var data: SearchActivityInit
    private lateinit var id: String

    val request = ApiRepository()
    val gson = Gson()
    private var isFavorite: Boolean = false
    private var menuItem: Menu? = null

    companion object {
        const val SET_PARCELABLE = "extra_person"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailmatch)

        initToolbar()

        data = intent.getParcelableExtra(SET_PARCELABLE) as SearchActivityInit
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
                    MatchFavoriteEntity.FAVORITE_MATCH,
                    MatchFavoriteEntity.MATCH_ID to data.idEvent,
                    MatchFavoriteEntity.HOME_TEAM_ID to data.idHomeTeam,
                    MatchFavoriteEntity.AWAY_TEAM_ID to data.idAwayTeam,
                    MatchFavoriteEntity.HOME_TEAM_NAME to data.strHomeTeam,
                    MatchFavoriteEntity.AWAY_TEAM_NAME to data.strAwayTeam,
                    MatchFavoriteEntity.HOME_SCORE to data.intHomeScore,
                    MatchFavoriteEntity.AWAY_SCORE to data.intAwayScore,
                    MatchFavoriteEntity.MATCH_DATE to data.dateEvent,
                    MatchFavoriteEntity.MATCH_TIME to data.strTimeLocal,
                    MatchFavoriteEntity.GOAL_HOME to data.strHomeGoalDetails,
                    MatchFavoriteEntity.GOAL_AWAY to data.strAwayGoalDetails,
                    MatchFavoriteEntity.GK_HOME to data.strHomeLineupGoalkeeper,
                    MatchFavoriteEntity.GK_AWAY to data.strAwayLineupGoalkeeper,
                    MatchFavoriteEntity.DEFF_HOME to data.strHomeLineupDefense,
                    MatchFavoriteEntity.DEFF_AWAY to data.strAwayLineupDefense,
                    MatchFavoriteEntity.FORWARD_HOME to data.strHomeLineupForward,
                    MatchFavoriteEntity.FORWARD_AWAY to data.strAwayLineupForward,
                    MatchFavoriteEntity.MID_HOME to data.strHomeLineupMidfield,
                    MatchFavoriteEntity.MID_AWAY to data.strAwayLineupMidfield,
                    MatchFavoriteEntity.SUB_HOME to data.strHomeLineupSubstitutes,
                    MatchFavoriteEntity.SUB_AWAY to data.strAwayLineupSubstitutes,
                    MatchFavoriteEntity.RED_HOME to data.strHomeRedCards,
                    MatchFavoriteEntity.RED_AWAY to data.strAwayRedCards,
                    MatchFavoriteEntity.YELLOW_HOME to data.strHomeYellowCards,
                    MatchFavoriteEntity.YELLOW_AWAY to data.strHomeYellowCards

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
                    MatchFavoriteEntity.FAVORITE_MATCH,
                    "(${MatchFavoriteEntity.MATCH_ID} = {matchId})",
                    "matchId" to id
                )
            }
            toast("dihapus")
        } catch (e: SQLiteConstraintException) {
        }
    }

    private fun favoriteState() {
        database.use {
            val result = select(MatchFavoriteEntity.FAVORITE_MATCH)
                .whereArgs(
                    "(MATCH_ID = {id})",
                    "id" to id
                )
            val favorite = result.parseList(classParser<MatchFavoriteEntity>())
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