package com.example.foryoudicodingkadesubtwo.view.activity

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuItemCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foryoudicodingkadesubtwo.R
import com.example.foryoudicodingkadesubtwo.R.menu.menu_search
import com.example.foryoudicodingkadesubtwo.SearchMatch.SearchMatchPresenter
import com.example.foryoudicodingkadesubtwo.SearchMatch.SearchMatchVIew
import com.example.foryoudicodingkadesubtwo.SearchMatch.SearchTeamPresenter
import com.example.foryoudicodingkadesubtwo.SearchMatch.SearchTeamVIew
import com.example.foryoudicodingkadesubtwo.adapter.SearchActivityAdapter
import com.example.foryoudicodingkadesubtwo.adapter.TeamListAdapter
import com.example.foryoudicodingkadesubtwo.helper.ApiRepository
import com.example.foryoudicodingkadesubtwo.view.fragment.FavoriteContentFragment
import com.example.foryoudicodingkadesubtwo.view.fragment.HomeContentFragment
import com.example.foryoudicodingkadesubtwo.view.model.SearchActivityInit
import com.example.foryoudicodingkadesubtwo.view.model.TeamListInit
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.content_home.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class HomeActivity : AppCompatActivity(), SearchMatchVIew, SearchTeamVIew {
    private lateinit var MatchAdapter: SearchActivityAdapter
    private lateinit var TeamAdapter: TeamListAdapter
    private var match: MutableList<SearchActivityInit> = mutableListOf()
    private var teams: MutableList<TeamListInit> = mutableListOf()
    private lateinit var Matchpresenter: SearchMatchPresenter
    private lateinit var Teampresenter: SearchTeamPresenter
    private var ToggleSearch = 0
    private var SearchQuery = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initToolbar()
        initMatchRecyclerview()
        initCOnfigToogle()

        bn_main.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        val fragment = HomeContentFragment()
        addFragment(fragment)

    }


    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    val fragment =
                        HomeContentFragment()
                    addFragment(fragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_favorites -> {
                    val fragment = FavoriteContentFragment()
                    addFragment(fragment)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fl_container, fragment, fragment.javaClass.simpleName)
            .commit()
    }

    private fun initMatchRecyclerview() {

        val request = ApiRepository()
        val gson = Gson()
        Matchpresenter = SearchMatchPresenter(this, request, gson)

        MatchAdapter = SearchActivityAdapter(match) {
            startActivity<DetailSearchActivity>(
                DetailSearchActivity.SET_PARCELABLE to it
            )
        }

        search_recycler.layoutManager = LinearLayoutManager(this)
        search_recycler.adapter = MatchAdapter

    }


    private fun initTeamRecyclerview() {

        val request = ApiRepository()
        val gson = Gson()
        Teampresenter = SearchTeamPresenter(this, request, gson)

        TeamAdapter = TeamListAdapter(teams) {
            startActivity<DetailTeamActivity>(
                DetailTeamActivity.SET_PARCELABLE to it
            )
        }

        search_recycler.layoutManager = LinearLayoutManager(this)
        search_recycler.adapter = TeamAdapter

    }

    override fun showLoading() {
//        progressBar.visible()
    }

    override fun hideLoading() {
//        progressBar.invisible()
    }

    override fun showMatchList(data: List<SearchActivityInit>) {
        if (data.size == 0) {
            toast("data tidak ditemukan")
        }

        match.clear()

        Log.d("jsonsearchmatch", data.toString())

        match.addAll(data)
        MatchAdapter.notifyDataSetChanged()
    }


    override fun showTeamList(data: List<TeamListInit>) {
        if (data.size == 0) {
            toast("data tidak ditemukan")
        }

        teams.clear()

        Log.d("jsonsearchteam", data.toString())

        teams.addAll(data)
        TeamAdapter.notifyDataSetChanged()
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(menu_search, menu)
        val action_search = menu.findItem(R.id.action_search)

        val searchView = MenuItemCompat.getActionView(action_search) as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {

                return false
            }

            override fun onQueryTextChange(query: String): Boolean {

                SearchQuery = query
                when (search_toggle.checkedTogglePosition) {
                    0 -> Matchpresenter.getSearchMatch(query)
                    1 -> Teampresenter.getSearchTeam(query)
                }

                return true
            }
        })

        MenuItemCompat.setOnActionExpandListener(
            action_search,
            object : MenuItemCompat.OnActionExpandListener {
                override fun onMenuItemActionCollapse(item: MenuItem): Boolean {
                    fl_container.visibility = View.VISIBLE
                    search_recycler.visibility = View.GONE
                    card_search_toggle.visibility = View.GONE
                    bn_main.visibility = View.VISIBLE
                    initToolbar()
                    return true
                }

                override fun onMenuItemActionExpand(item: MenuItem): Boolean {
                    fl_container.visibility = View.GONE
                    search_recycler.visibility = View.VISIBLE
                    card_search_toggle.visibility = View.VISIBLE
                    bn_main.visibility = View.GONE
                    return true
                }
            })
        return true
    }

    fun initCOnfigToogle() {

        search_toggle.setOnToggleSwitchChangeListener { position, isChecked ->
            when (position) {
                0 -> {
                    initMatchRecyclerview()
                    Log.d("tooglematch", SearchQuery)
                    Matchpresenter.getSearchMatch(SearchQuery)
                    ToggleSearch = position
                }
                1 -> {
                    initTeamRecyclerview()
                    Log.d("toogleteam", SearchQuery)
                    Teampresenter.getSearchTeam(SearchQuery)
                    ToggleSearch = position
                }
            }
        }

    }

    fun initToolbar() {
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar!!.title = " "
        //set back button
    }
}
