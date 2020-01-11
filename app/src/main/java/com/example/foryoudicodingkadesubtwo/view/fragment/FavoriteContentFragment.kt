package com.example.foryoudicodingkadesubtwo.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.foryoudicodingkadesubtwo.R
import com.example.foryoudicodingkadesubtwo.adapter.AdapterTabLayout
import kotlinx.android.synthetic.main.fragmentcontent_favorites.*


class FavoriteContentFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragmentcontent_favorites, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initComponent()
    }

    private fun initComponent() {
        setupViewPager(viewpager_main)
        tablayout_main.setupWithViewPager(viewpager_main)
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = AdapterTabLayout(childFragmentManager)
        adapter.addFragment(FavoritesMatchFragment(), "Match")
        adapter.addFragment(FavoritesTeamFragment(), "Team")
        viewPager.adapter = adapter

    }

}