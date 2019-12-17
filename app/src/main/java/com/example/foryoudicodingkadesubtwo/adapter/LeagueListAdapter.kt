package com.example.foryoudicodingkadesubtwo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foryoudicodingkadesubtwo.R
import com.example.foryoudicodingkadesubtwo.view.model.LeagueListInit
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_listleague.view.*

class LeagueListAdapter(private val listHero: ArrayList<LeagueListInit>) :
    RecyclerView.Adapter<LeagueListAdapter.ListViewHolder>() {
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_listleague, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listHero[position])
    }

    override fun getItemCount(): Int = listHero.size

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(hero: LeagueListInit) {
            with(itemView) {
                itemView.titleleague.text = hero.name
                itemView.negara.text = hero.negara
                itemView.firstleague.text = hero.firstleague
                hero.logo?.let { Picasso.get().load(it).into(itemView.imagelist) }

                itemView.setOnClickListener { onItemClickCallback?.onItemClicked(hero) }
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: LeagueListInit)
    }
}