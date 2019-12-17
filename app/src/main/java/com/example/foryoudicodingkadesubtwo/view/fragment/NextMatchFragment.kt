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
import com.example.foryoudicodingkadesubtwo.R
import com.example.foryoudicodingkadesubtwo.adapter.NextMatchAdapter
import com.example.foryoudicodingkadesubtwo.helper.setIDleague
import com.example.foryoudicodingkadesubtwo.view.activity.DetailMatchNextActivity
import com.example.foryoudicodingkadesubtwo.view.model.NextMatchInit
import com.example.foryoudicodingkadesubtwo.viewmodel.NextMatchViewModel
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

class NextMatchFragment : Fragment() {

    private lateinit var mainViewModel: NextMatchViewModel
    private lateinit var mAdapter: NextMatchAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_nextmatch, container, false)


        val recyclerViewMain = view.findViewById(R.id.recyclerViewMain) as RecyclerView
        mAdapter = NextMatchAdapter()
        mAdapter.notifyDataSetChanged()
        mAdapter.setOnItemClickCallback(object : NextMatchAdapter.OnItemClickCallback{
            override fun onItemClicked(data: NextMatchInit) {
                toast("Hello, ${data.idEvent}, Opened")
                startActivity<DetailMatchNextActivity>(
                    DetailMatchNextActivity.SET_PARCELABLE to data)

            }
        })

        recyclerViewMain.layoutManager = LinearLayoutManager(context)
        recyclerViewMain.adapter = mAdapter

        mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            NextMatchViewModel::class.java)

        mainViewModel.setListNextMatch(context?.let {
            setIDleague.getToken(
                it
            )
        })



        mainViewModel.getListNextMatch().observe(this, Observer {
            it?.also {
                if (it == null) {
                    toast("Detail init null")
                }

                if (it.size <= 0) {
                    toast("Hello, ${it.size}, Opened")
                }
                if (it != null) {

                    Log.d("jsonNext", it.toString())

                    mAdapter.setData(it)


                }
            }
        })
        return view
    }
}