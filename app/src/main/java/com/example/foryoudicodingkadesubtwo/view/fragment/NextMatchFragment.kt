package com.example.foryoudicodingkadesubtwo.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foryoudicodingkadesubtwo.helper.ApiRepository
import com.example.foryoudicodingkadesubtwo.NextMatch.NextMatchPresenter
import com.example.foryoudicodingkadesubtwo.NextMatch.NextView
import com.example.foryoudicodingkadesubtwo.R
import com.example.foryoudicodingkadesubtwo.adapter.NextMatchAdapter
import com.example.foryoudicodingkadesubtwo.helper.setIDleague
import com.example.foryoudicodingkadesubtwo.view.activity.DetailMatchNextActivity
import com.example.foryoudicodingkadesubtwo.view.model.NextMatchInit
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_nextmatch.*
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast


class NextMatchFragment : Fragment(), NextView {

    private lateinit var mAdapter: NextMatchAdapter
    private var teams: MutableList<NextMatchInit> = mutableListOf()
    private lateinit var presenter: NextMatchPresenter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setPresent()
        setRecycler()
//
    }

    fun setPresent(){

        val request = ApiRepository()
        val gson = Gson()
        presenter = NextMatchPresenter(this, request, gson)

        presenter.getNextMatchList(context?.let { setIDleague.getToken(it) })

    }

    fun setRecycler(){
        mAdapter = NextMatchAdapter(teams) {
            toast("Hello, ${it.idEvent}, Opened")
            startActivity<DetailMatchNextActivity>(DetailMatchNextActivity.SET_PARCELABLE to it)

        }
        mAdapter.notifyDataSetChanged()

        recyclerViewMain.layoutManager = LinearLayoutManager(context)
        recyclerViewMain.adapter = mAdapter

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_listcontent, container, false)

    }

    override fun showLoading() {
//        progressBar.visible()
    }

    override fun hideLoading() {
//        progressBar.invisible()
    }

    override fun showTeamList(datase: List<NextMatchInit>) {

        if(datase.size == null){
            toast("data kosong")
        }else{

            teams.clear()

            Log.d("jsonres", datase.toString())

            teams.addAll(datase)
            mAdapter.notifyDataSetChanged()
        }


    }
}