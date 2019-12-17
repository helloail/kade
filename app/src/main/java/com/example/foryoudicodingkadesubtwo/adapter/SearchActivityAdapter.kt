package com.example.foryoudicodingkadesubtwo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foryoudicodingkadesubtwo.R
import com.example.foryoudicodingkadesubtwo.view.model.SearchActivityInit
import kotlinx.android.synthetic.main.item_listmatch.view.*

class SearchActivityAdapter : RecyclerView.Adapter<SearchActivityAdapter.WeatherViewHolder>() {
    private val mData = ArrayList<SearchActivityInit>()

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    /**
     * Gunakan method ini jika semua datanya akan diganti
     *
     * @param items kumpulan data baru yang akan mengganti semua data yang sudah ada
     */
    fun setData(items: ArrayList<SearchActivityInit>) {
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }

    /**
     * Gunakan method ini jika ada 1 data yang ditambahkan
     *
     * @param item data baru yang akan ditambahkan
     */
    fun addItem(item: SearchActivityInit) {
        mData.add(item)
        notifyDataSetChanged()
    }

    fun clearData() {
        mData.clear()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): WeatherViewHolder {
        val mView = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_listmatch, viewGroup, false)
        return WeatherViewHolder(mView)
    }

    override fun onBindViewHolder(weatherViewHolder: WeatherViewHolder, position: Int) {
        weatherViewHolder.bind(mData[position])
    }

    override fun getItemCount(): Int = mData.size

    inner class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(match: SearchActivityInit) {
            with(itemView) {
                itemView.listTeamAwway.text = match.strHomeTeam
                itemView.listTeamHome.text = match.strAwayTeam
                itemView.away_score.text = match.intAwayScore
                itemView.home_score.text = match.intHomeScore
                itemView.title_time.text = match.dateEvent + " " + match.strTimeLocal


                itemView.setOnClickListener { onItemClickCallback?.onItemClicked(match) }
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: SearchActivityInit)
    }
}