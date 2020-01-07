package com.example.foryoudicodingkadesubtwo.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foryoudicodingkadesubtwo.R
import com.example.foryoudicodingkadesubtwo.adapter.LeagueListAdapter
import com.example.foryoudicodingkadesubtwo.helper.setIDleague
import com.example.foryoudicodingkadesubtwo.view.activity.DetailLeagueActivity
import com.example.foryoudicodingkadesubtwo.view.model.LeagueListInit
import kotlinx.android.synthetic.main.content_listleague.view.*
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

class ListLeagueFragment : Fragment() {


    private val items = ArrayList<LeagueListInit>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.content_listleague, container, false)

        initData()


        view.recyclerView.layoutManager = LinearLayoutManager(context)
        val mAdapter = LeagueListAdapter(items)
        view.recyclerView.adapter = mAdapter

        mAdapter.setOnItemClickCallback(object :
            LeagueListAdapter.OnItemClickCallback {
            override fun onItemClicked(data: LeagueListInit) {
                toast("Hello, ${data.name}, Opened")
                data.idleague?.let {
                    context?.let { it1 ->
                        setIDleague.setToken(it1, it)
                    }
                }

                startActivity<DetailLeagueActivity>(
                    DetailLeagueActivity.SET_PARCELABLE to data
                )

            }
        })

        return view


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }


    fun initData() {
        val firstleague = resources.getStringArray(R.array.firstleague)
        val negara = resources.getStringArray(R.array.negara)
        val name = resources.getStringArray(R.array.liganame)
        val idleague = resources.getStringArray(R.array.idleague)
        val logo = resources.obtainTypedArray(R.array.logo_liga)
        items.clear()
        for (i in name.indices) {
            items.add(

                LeagueListInit(
                    idleague[i],
                    name[i],
                    negara[i],
                    firstleague[i],
                    logo.getResourceId(i, 0)
                )
            )
        }

        //Recycle the typed array
        logo.recycle()
    }
}
