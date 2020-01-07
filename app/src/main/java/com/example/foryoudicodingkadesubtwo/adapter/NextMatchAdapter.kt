package com.example.foryoudicodingkadesubtwo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foryoudicodingkadesubtwo.view.model.NextMatchInit
import com.example.foryoudicodingkadesubtwo.R
import kotlinx.android.synthetic.main.item_listmatch.view.*
import kotlinx.android.synthetic.main.item_listmatch.view.title_time


class NextMatchAdapter( private val favorite: List<NextMatchInit>,
private val listener: (NextMatchInit) -> Unit

) : RecyclerView.Adapter<NextMatchAdapter.FavoriteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val mView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_listmatch, parent, false)
        return FavoriteViewHolder(mView)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bindItem(favorite[position], listener)
    }

    override fun getItemCount(): Int = favorite.size


    class FavoriteViewHolder(view: View) : RecyclerView.ViewHolder(view) {


        fun bindItem(match: NextMatchInit, listener: (NextMatchInit) -> Unit) {

            itemView.listTeamAwway.text = match.strHomeTeam
            itemView.listTeamHome.text = match.strAwayTeam
            itemView.away_score.text = " "
            itemView.home_score.text = " "
            itemView.title_time.text = match.dateEvent+" "+match.strTimeLocal
            itemView.setOnClickListener { listener(match) }

        }
    }
}