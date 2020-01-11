package com.example.foryoudicodingkadesubtwo.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foryoudicodingkadesubtwo.NextMatch.NextMatchPresenter
import com.example.foryoudicodingkadesubtwo.PastMatch.PastMatchView
import com.example.foryoudicodingkadesubtwo.PastMatch.StandingMatchPresenter
import com.example.foryoudicodingkadesubtwo.PastMatch.StandingMatchView
import com.example.foryoudicodingkadesubtwo.R
import com.example.foryoudicodingkadesubtwo.adapter.NextMatchAdapter
import com.example.foryoudicodingkadesubtwo.adapter.StandMatchAdapter
import com.example.foryoudicodingkadesubtwo.helper.ApiRepository
import com.example.foryoudicodingkadesubtwo.helper.setIDleague
import com.example.foryoudicodingkadesubtwo.view.model.NextMatchInit
import com.example.foryoudicodingkadesubtwo.view.model.PastMatchInit
import com.example.foryoudicodingkadesubtwo.view.model.StandingInit
import com.google.gson.Gson
import kotlinx.android.synthetic.main.layoutitem_standing.*
import org.jetbrains.anko.support.v4.toast

class StandingMatchFragment: Fragment(), StandingMatchView {

    private lateinit var mAdapter: StandMatchAdapter
    private var teams: MutableList<StandingInit> = mutableListOf()
    private lateinit var presenter: StandingMatchPresenter


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setPresent()
        setRecycler()

    }

    fun setRecycler() {
        mAdapter = StandMatchAdapter(teams) {
        }
        mAdapter.notifyDataSetChanged()

        recyclerViewMain.layoutManager = LinearLayoutManager(context)
        recyclerViewMain.adapter = mAdapter

    }

    fun setPresent() {

        val request = ApiRepository()
        val gson = Gson()
        presenter = StandingMatchPresenter(this, request, gson)

        presenter.getPastMatchList(context?.let { setIDleague.getToken(it) })

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layoutitem_standing, container, false)

    }

    override fun showLoading() {
//        progressBar.visible()
    }

    override fun hideLoading() {
//        progressBar.invisible()
    }

    override fun showTeamList(data: List<StandingInit>) {

        if (data.size == null) {
            toast("data kosong")
        } else {
            teams.clear()

            Log.d("jsonres", data.toString())

            teams.addAll(data)
            mAdapter.notifyDataSetChanged()
        }
    }
}