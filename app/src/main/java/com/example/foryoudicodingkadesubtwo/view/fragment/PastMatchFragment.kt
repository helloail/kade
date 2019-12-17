package com.example.foryoudicodingkadesubtwo.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foryoudicodingkadesubtwo.view.activity.DetailMatchPastActivity
import com.example.foryoudicodingkadesubtwo.adapter.PastMatchAdapter
import com.example.foryoudicodingkadesubtwo.R
import com.example.foryoudicodingkadesubtwo.helper.setIDleague
import com.example.foryoudicodingkadesubtwo.view.model.PastMatchInit
import com.example.foryoudicodingkadesubtwo.viewmodel.PastMatchViewModel
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.support.v4.startActivity


class PastMatchFragment : Fragment() {
    private lateinit var mainViewModel: PastMatchViewModel
    private lateinit var mAdapter: PastMatchAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pastmatch, container, false)


        val recyclerViewMain = view.findViewById(R.id.recyclerViewMain) as RecyclerView
        mAdapter = PastMatchAdapter()
        mAdapter.notifyDataSetChanged()

        recyclerViewMain.layoutManager = LinearLayoutManager(context)
        recyclerViewMain.adapter = mAdapter

        mAdapter.setOnItemClickCallback(object :
            PastMatchAdapter.OnItemClickCallback {
            override fun onItemClicked(data: PastMatchInit) {

                startActivity<DetailMatchPastActivity>(
                    DetailMatchPastActivity.SET_PARCELABLE to data)

            }
        })

        mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            PastMatchViewModel::class.java)

        mainViewModel.setListPastMatch(context?.let {
            setIDleague.getToken(
                it
            )
        })



        mainViewModel.getListPastMatch().observe(this, Observer {
            it?.also {
                if (it == null) {
                    toast("Detail init null")
                }


                if (it != null) {

                    Log.d("jsonpast", it.toString())

                    mAdapter.setData(it)


                }
            }
        })

        return view
    }



}