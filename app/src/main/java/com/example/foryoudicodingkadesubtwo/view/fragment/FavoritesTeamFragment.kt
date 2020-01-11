package com.example.foryoudicodingkadesubtwo.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foryoudicodingkadesubtwo.R
import com.example.foryoudicodingkadesubtwo.adapter.FavoritesTeamAdapter
import com.example.foryoudicodingkadesubtwo.helper.database
import com.example.foryoudicodingkadesubtwo.view.activity.DetailTeamActivity
import com.example.foryoudicodingkadesubtwo.view.activity.DetailTeamFavoritesActivity
import com.example.foryoudicodingkadesubtwo.view.model.TeamFavoriteEntity
import kotlinx.android.synthetic.main.fragmentfavorites.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.startActivity

class FavoritesTeamFragment : Fragment() {


    private var favorites: MutableList<TeamFavoriteEntity> = mutableListOf()
    private lateinit var madapter: FavoritesTeamAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_listcontent, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecycler()
        showFavorite()

    }

    fun initRecycler() {
        madapter = FavoritesTeamAdapter(favorites) {
            context?.startActivity<DetailTeamFavoritesActivity>(DetailTeamFavoritesActivity.SET_PARCELABLE to it)
        }
        recyclerViewMain.layoutManager = LinearLayoutManager(context)
        recyclerViewMain.adapter = madapter
        recyclerViewMain.setHasFixedSize(true)

    }


    private fun showFavorite() {
        favorites.clear()
        context?.database?.use {
            val result = select(TeamFavoriteEntity.FAVORITE_TEAM)
            val favorite = result.parseList(classParser<TeamFavoriteEntity>())
            Log.d("resulttes", favorite.toString())
            favorites.addAll(favorite)
            madapter.notifyDataSetChanged()
        }
    }
}