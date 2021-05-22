package com.example.cleanarchitecturedemo.views.onboarding

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

private const val DEFAULT_VIEW_COUNT = 4
class OnBoardingViewPagerAdapter(fa : FragmentActivity) : FragmentStateAdapter(fa) {
    private var totalViewCount  = DEFAULT_VIEW_COUNT

    //allow to customize view count
    fun updateViewCount(count : Int) {
        totalViewCount = count
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return totalViewCount
    }

    override fun createFragment(position: Int): Fragment {
        return OnBoardFragment.newInstance(position)
    }

}