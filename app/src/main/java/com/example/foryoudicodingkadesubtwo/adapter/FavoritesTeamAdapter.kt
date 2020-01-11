package com.example.foryoudicodingkadesubtwo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foryoudicodingkadesubtwo.R
import com.example.foryoudicodingkadesubtwo.view.model.TeamFavoriteEntity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_listleague.view.*

class FavoritesTeamAdapter(
    private val favorite: List<TeamFavoriteEntity>,
    private val listener: (TeamFavoriteEntity) -> Unit
) : RecyclerView.Adapter<FavoritesTeamAdapter.FavoriteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val mView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_listleague, parent, false)
        return FavoriteViewHolder(mView)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bindItem(favorite[position], listener)
    }

    override fun getItemCount(): Int = favorite.size

    class FavoriteViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindItem(team: TeamFavoriteEntity, listener: (TeamFavoriteEntity) -> Unit) {

            itemView.titleleague.text = team.stTeam
            itemView.negara.text = team.stStadium
            itemView.firstleague.text = team.inFormedYear
            team.stTeamBadge?.let { Picasso.get().load(it).into(itemView.imagelist) }
            itemView.setOnClickListener { listener(team) }

        }
    }
}