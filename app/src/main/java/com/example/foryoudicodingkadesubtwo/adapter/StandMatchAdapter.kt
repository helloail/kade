package com.example.foryoudicodingkadesubtwo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foryoudicodingkadesubtwo.R
import com.example.foryoudicodingkadesubtwo.view.model.StandingInit
import kotlinx.android.synthetic.main.item_standing.view.*

class StandMatchAdapter (private val favorite: List<StandingInit>,
                         private val listener: (StandingInit) -> Unit
) : RecyclerView.Adapter<StandMatchAdapter.FavoriteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val mView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_standing, parent, false)
        return FavoriteViewHolder(mView)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bindItem(favorite[position], listener)
    }

    override fun getItemCount(): Int = favorite.size


    class FavoriteViewHolder(view: View) : RecyclerView.ViewHolder(view) {


        fun bindItem(team: StandingInit, listener: (StandingInit) -> Unit) {

            itemView.standing_team.text = team.name
            itemView.standing_play.text = team.played
            itemView.standing_win.text = team.win
            itemView.standing_draw.text = team.draw
            itemView.standing_lose.text = team.loss
            itemView.standing_gf.text = team.goalsfor
            itemView.standing_ga.text = team.goalsagainst
            itemView.standing_gd.text = team.goalsdifference
            itemView.standing_points.text = team.total
            itemView.setOnClickListener { listener(team) }

        }
    }
}