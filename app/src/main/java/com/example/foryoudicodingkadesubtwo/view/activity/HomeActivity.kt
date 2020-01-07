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
import com.example.foryoudicodingkadesubtwo.helper.ApiRepository
import com.example.foryoudicodingkadesubtwo.R
import com.example.foryoudicodingkadesubtwo.R.menu.menu_search
import com.example.foryoudicodingkadesubtwo.Search.SearchMatchPresenter
import com.example.foryoudicodingkadesubtwo.Search.SearchMatchVIew
import com.example.foryoudicodingkadesubtwo.adapter.SearchActivityAdapter
import com.example.foryoudicodingkadesubtwo.view.fragment.FavoritesFragment
import com.example.foryoudicodingkadesubtwo.view.fragment.ListLeagueFragment
import com.example.foryoudicodingkadesubtwo.view.model.SearchActivityInit
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.content_home.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class HomeActivity : AppCompatActivity(), SearchMatchVIew {
    private lateinit var mAdapter: SearchActivityAdapter
    private var teams: MutableList<SearchActivityInit> = mutableListOf()
    private lateinit var presenter: SearchMatchPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initToolbar()
        initRecyclerview()
        bn_main.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        val fragment = ListLeagueFragment()
        addFragment(fragment)


    }


    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    val fragment =
                        ListLeagueFragment()
                    addFragment(fragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_favorites -> {
                    val fragment = FavoritesFragment()
                    addFragment(fragment)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fl_container, fragment, fragment.javaClass.getSimpleName())
            .commit()
    }

    private fun initRecyclerview() {

        val request = ApiRepository()
        val gson = Gson()
        presenter = SearchMatchPresenter(this, request, gson)

        mAdapter = SearchActivityAdapter(teams) {
            startActivity<DetailSearchActivity>(
                DetailSearchActivity.SET_PARCELABLE to it
            )
        }


        search_recycler.layoutManager = LinearLayoutManager(this)
        search_recycler.adapter = mAdapter


    }

    override fun showLoading() {
//        progressBar.visible()
    }

    override fun hideLoading() {
//        progressBar.invisible()
    }

    override fun showTeamList(data: List<SearchActivityInit>) {
        if (data.size == 0) {
            toast("data tidak ditemukan")
        }



        teams.clear()

        Log.d("jsonres", data.toString())


        teams.addAll(data)
        mAdapter.notifyDataSetChanged()
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
//                sViewModel.setSearchaMatch(query)
                presenter.getSearchMatch(query)

                return true
            }
        })


        MenuItemCompat.setOnActionExpandListener(
            action_search,
            object : MenuItemCompat.OnActionExpandListener {
                override fun onMenuItemActionCollapse(item: MenuItem): Boolean {
                    fl_container.setVisibility(View.VISIBLE)
                    search_recycler.setVisibility(View.GONE)

                    bn_main.setVisibility(View.VISIBLE)

                    initToolbar()

                    return true
                }

                override fun onMenuItemActionExpand(item: MenuItem): Boolean {
                    fl_container.setVisibility(View.GONE)
                    search_recycler.setVisibility(View.VISIBLE)
                    bn_main.setVisibility(View.GONE)
                    return true
                }
            })
        return true
    }

    fun initToolbar() {
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar!!.title = " "
        //set back button
    }
}
