package com.example.foryoudicodingkadesubtwo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foryoudicodingkadesubtwo.view.model.NextMatchInit
import com.example.foryoudicodingkadesubtwo.R
import kotlinx.android.synthetic.main.item_listmatch.view.*
import kotlinx.android.synthetic.main.item_listmatch.view.title_time


class NextMatchAdapter : RecyclerView.Adapter<NextMatchAdapter.WeatherViewHolder>() {
    private val mData = ArrayList<NextMatchInit>()

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    /**
     * Gunakan method ini jika semua datanya akan diganti
     *
     * @param items kumpulan data baru yang akan mengganti semua data yang sudah ada
     */
    fun setData(items: ArrayList<NextMatchInit>) {
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }

    /**
     * Gunakan method ini jika ada 1 data yang ditambahkan
     *
     * @param item data baru yang akan ditambahkan
     */
    fun addItem(item: NextMatchInit) {
        mData.add(item)
        notifyDataSetChanged()
    }

    fun clearData() {
        mData.clear()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): WeatherViewHolder {
        val mView = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_listmatch, viewGroup, false)
        return WeatherViewHolder(mView)
    }

    override fun onBindViewHolder(weatherViewHolder: WeatherViewHolder, position: Int) {
        weatherViewHolder.bind(mData[position])
    }

    override fun getItemCount(): Int = mData.size

    inner class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(match: NextMatchInit) {
            with(itemView){

                itemView.listTeamAwway.text = match.strHomeTeam
                itemView.listTeamHome.text = match.strAwayTeam
                itemView.away_score.text = " "
                itemView.home_score.text = " "
                itemView.title_time.text = match.dateEvent+" "+match.strTimeLocal

                itemView.setOnClickListener { onItemClickCallback?.onItemClicked(match) }
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: NextMatchInit)
    }
}


