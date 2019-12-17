package com.example.foryoudicodingkadesubtwo.view.activity

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.example.foryoudicodingkadesubtwo.R
import com.example.foryoudicodingkadesubtwo.adapter.AdapterTabLayout
import com.example.foryoudicodingkadesubtwo.view.fragment.NextMatchFragment
import com.example.foryoudicodingkadesubtwo.view.fragment.PastMatchFragment
import com.example.foryoudicodingkadesubtwo.view.model.DetailLeagueInit
import com.example.foryoudicodingkadesubtwo.view.model.LeagueListInit
import com.example.foryoudicodingkadesubtwo.viewmodel.DetailLeagueViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detailleague.*
import org.jetbrains.anko.toast

class DetailLeagueActivity : AppCompatActivity() {


    private lateinit var mainViewModel: DetailLeagueViewModel
    private lateinit var data: LeagueListInit


    companion object {
        const val SET_PARCELABLE = "extra_person"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        data = intent.getParcelableExtra(SET_PARCELABLE) as LeagueListInit


        setContentView(R.layout.activity_detailleague)
        initComponent()
        initToolbar()



        mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            DetailLeagueViewModel::class.java
        )
        mainViewModel.setListmovie(data.idleague)
//        setListmovie(data.idleague)

        mainViewModel.getListMovie().observe(this, Observer {
            it?.also {
                if (it == null) {
                    toast("Detail init null")
                }

                if (it.size <= 0) {
                    toast("Hello, ${it.size}, Opened")
                }
                if (it != null) {

                    Log.d("jsondetail", it.toString())

                    showGoalDetailsSection(it)


                }
            }
        })


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
        val id = item.getItemId()
        if (id == R.id.action_search) {
            Toast.makeText(this, "Item One Clicked", Toast.LENGTH_LONG).show()
            return true
        }


        return super.onOptionsItemSelected(item)
    }

    private fun showGoalDetailsSection(League: List<DetailLeagueInit>) {
        val team = League?.get(0)
        title_liga.text = team?.strLeague
        title_gender.text = team?.strGender
        title_negara.text = team?.strCountry

        team?.strBadge.let { Picasso.get().load(it).into(image_logo) }

    }

    private fun initComponent() {
        setupViewPager(viewpager_main)
        tablayout_main.setupWithViewPager(viewpager_main)
    }


    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = AdapterTabLayout(supportFragmentManager)
        adapter.addFragment(PastMatchFragment(), "Last Match")
        adapter.addFragment(NextMatchFragment(), "Next Match")
        viewPager.adapter = adapter

    }
}