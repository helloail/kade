package com.example.foryoudicodingkadesubtwo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foryoudicodingkadesubtwo.R
import com.example.foryoudicodingkadesubtwo.view.model.TeamListInit
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_listleague.view.*

class TeamListAdapter(
    private val favorite: List<TeamListInit>,
    private val listener: (TeamListInit) -> Unit
) : RecyclerView.Adapter<TeamListAdapter.FavoriteViewHolder>() {

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


        fun bindItem(team: TeamListInit, listener: (TeamListInit) -> Unit) {

            itemView.titleleague.text = team.strTeam
            itemView.negara.text = team.strStadium
            itemView.firstleague.text = team.intFormedYear
            team.strTeamBadge?.let { Picasso.get().load(it).into(itemView.imagelist) }
            itemView.setOnClickListener { listener(team) }

        }
    }
}