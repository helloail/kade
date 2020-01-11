package com.example.foryoudicodingkadesubtwo.view.activity

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.foryoudicodingkadesubtwo.DetailLeague.DetailLeaguePresenter
import com.example.foryoudicodingkadesubtwo.DetailLeague.DetailLeagueView
import com.example.foryoudicodingkadesubtwo.R
import com.example.foryoudicodingkadesubtwo.adapter.AdapterTabLayout
import com.example.foryoudicodingkadesubtwo.helper.ApiRepository
import com.example.foryoudicodingkadesubtwo.view.fragment.NextMatchFragment
import com.example.foryoudicodingkadesubtwo.view.fragment.PastMatchFragment
import com.example.foryoudicodingkadesubtwo.view.fragment.StandingMatchFragment
import com.example.foryoudicodingkadesubtwo.view.fragment.TeamListFragment
import com.example.foryoudicodingkadesubtwo.view.model.DetailLeagueInit
import com.example.foryoudicodingkadesubtwo.view.model.LeagueListInit
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detailleague.*

class DetailLeagueActivity : AppCompatActivity(),
    DetailLeagueView {


    private lateinit var data: LeagueListInit
    private lateinit var presenter: DetailLeaguePresenter

    companion object {
        const val SET_PARCELABLE = "extra_person"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        data = intent.getParcelableExtra(SET_PARCELABLE) as LeagueListInit
        setContentView(R.layout.activity_detailleague)
        initComponent()
        initToolbar()
        observe()

    }

    fun observe() {
        val request = ApiRepository()
        val gson = Gson()
        presenter = DetailLeaguePresenter(this, request, gson)

        presenter.getDetailLeague(data.idleague)

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


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here.
        val id = item.itemId
        if (id == R.id.action_search) {
            Toast.makeText(this, "Item One Clicked", Toast.LENGTH_LONG).show()
            return true
        }
        return super.onOptionsItemSelected(item)
    }


    override fun showLoading() {
//        progressBar.visible()
    }

    override fun hideLoading() {
//        progressBar.invisible()
    }

    override fun showTeamList(data: List<DetailLeagueInit>) {
        val team = data.get(0)
        title_liga.text = team.strLeague
        title_gender.text = team.strGender
        title_negara.text = team.strCountry

        team.strBadge.let { Picasso.get().load(it).into(image_logo) }

    }

    private fun initComponent() {
        setupViewPager(viewpager_main)
        tablayout_main.setupWithViewPager(viewpager_main)
    }


    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = AdapterTabLayout(supportFragmentManager)
        adapter.addFragment(PastMatchFragment(), "Last Match")
        adapter.addFragment(NextMatchFragment(), "Next Match")
        adapter.addFragment(StandingMatchFragment(), "Standing")
        adapter.addFragment(TeamListFragment(), "Team")
        viewPager.adapter = adapter

    }
}