package com.example.foryoudicodingkadesubtwo.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foryoudicodingkadesubtwo.view.model.FavoriteEntity
import com.example.foryoudicodingkadesubtwo.R
import com.example.foryoudicodingkadesubtwo.adapter.FavoritesAdapter
import com.example.foryoudicodingkadesubtwo.helper.database
import com.example.foryoudicodingkadesubtwo.view.activity.DetailFavoritesActivity
import kotlinx.android.synthetic.main.fragmentfavorites.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.startActivity

class FavoritesFragment : Fragment() {

    private var favorites: MutableList<FavoriteEntity> = mutableListOf()
    private lateinit var madapter: FavoritesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragmentfavorites, container, false)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        madapter = FavoritesAdapter(favorites) {
            context?.startActivity<DetailFavoritesActivity>(DetailFavoritesActivity.SET_PARCELABLE to it)
        }
        recyclerViewMain.layoutManager = LinearLayoutManager(context)
        recyclerViewMain.adapter = madapter

        showFavorite()

    }

    private fun showFavorite() {
        favorites.clear()
        context?.database?.use {
            val result = select(FavoriteEntity.FAVORITE_MATCH)
            val favorite = result.parseList(classParser<FavoriteEntity>())
            Log.d("resulttes",favorite.toString())
            favorites.addAll(favorite)
            madapter.notifyDataSetChanged()
        }
    }

}