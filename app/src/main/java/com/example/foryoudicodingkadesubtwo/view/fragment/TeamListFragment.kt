package com.example.foryoudicodingkadesubtwo.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foryoudicodingkadesubtwo.R
import com.example.foryoudicodingkadesubtwo.TeamList.TeamListPresenter
import com.example.foryoudicodingkadesubtwo.TeamList.TeamVIew
import com.example.foryoudicodingkadesubtwo.adapter.TeamListAdapter
import com.example.foryoudicodingkadesubtwo.helper.ApiRepository
import com.example.foryoudicodingkadesubtwo.helper.setIDleague
import com.example.foryoudicodingkadesubtwo.view.activity.DetailTeamActivity
import com.example.foryoudicodingkadesubtwo.view.model.TeamListInit
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_listcontent.*
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

class TeamListFragment : Fragment(), TeamVIew {

    private lateinit var mAdapter: TeamListAdapter
    private var teams: MutableList<TeamListInit> = mutableListOf()
    private lateinit var presenter: TeamListPresenter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setPresent()
        setRecycler()
//
    }


    fun setPresent() {

        val request = ApiRepository()
        val gson = Gson()
        presenter = TeamListPresenter(this, request, gson)

        presenter.getTeamList(context?.let { setIDleague.getToken(it) })

    }

    fun setRecycler() {

        mAdapter = TeamListAdapter(teams) {
            startActivity<DetailTeamActivity>(DetailTeamActivity.SET_PARCELABLE to it)
        }
        mAdapter.notifyDataSetChanged()

        recyclerViewMain.layoutManager = LinearLayoutManager(context)
        recyclerViewMain.adapter = mAdapter

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_listcontent, container, false)

    }


    override fun showLoading() {
//        progressBar.visible()
    }

    override fun hideLoading() {
//        progressBar.invisible()
    }

    override fun showTeamList(data: List<TeamListInit>) {

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