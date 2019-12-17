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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foryoudicodingkadesubtwo.R
import com.example.foryoudicodingkadesubtwo.R.menu.menu_search
import com.example.foryoudicodingkadesubtwo.adapter.SearchActivityAdapter
import com.example.foryoudicodingkadesubtwo.view.fragment.FavoritesFragment
import com.example.foryoudicodingkadesubtwo.view.fragment.ListLeagueFragment
import com.example.foryoudicodingkadesubtwo.view.model.SearchActivityInit
import com.example.foryoudicodingkadesubtwo.viewmodel.SearchViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.content_home.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class HomeActivity : AppCompatActivity() {
    private lateinit var sViewModel: SearchViewModel

    private lateinit var sdapter: SearchActivityAdapter

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

        sdapter = SearchActivityAdapter()
        sdapter.notifyDataSetChanged()
        search_recycler.setLayoutManager(LinearLayoutManager(this))
        search_recycler.setHasFixedSize(true)
        search_recycler.setItemAnimator(DefaultItemAnimator())
        search_recycler.setAdapter(sdapter)
        sdapter.setOnItemClickCallback(object : SearchActivityAdapter.OnItemClickCallback{
            override fun onItemClicked(data: SearchActivityInit) {
                toast("Hello, ${data.idEvent}, Opened")
                startActivity<DetailSearchActivity>(DetailSearchActivity.SET_PARCELABLE to data)
            }
        })
        sViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            SearchViewModel::class.java
        )


        sViewModel.getSearchMatch().observe(this, Observer {
            it?.also {
                if (it == null) {
                    toast("Detail init null")
                }

                if (it.size <= 0) {

                    toast("Detail tidak ditemukan")
                }
                if (it != null) {

                    Log.d("jsonsearc", it.toString())

                    sdapter.setData(it)


                }

            }
        })
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
                sViewModel.setSearchaMatch(query)
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