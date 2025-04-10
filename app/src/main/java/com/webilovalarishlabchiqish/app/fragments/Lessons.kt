package com.webilovalarishlabchiqish.app.fragments


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.webilovalarishlabchiqish.app.R
import com.webilovalarishlabchiqish.app.adapters.ViewPagerAdapter
import com.webilovalarishlabchiqish.app.databinding.FragmentLessonsBinding

class LessonsFragment : Fragment() {

    private lateinit var binding: FragmentLessonsBinding
    private lateinit var fragmentContext: Context



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_lessons, container, false)

        binding = FragmentLessonsBinding.bind(view)


        val viewPager: ViewPager = binding.viewPager
        val tabLayout: TabLayout = binding.tabLayout

        val adapter = ViewPagerAdapter(childFragmentManager)
        adapter.addFragment(Video(), getString(R.string.videodarslar))
        adapter.addFragment(Darslik(), getString(R.string.darsliklar))

        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)

        return binding.root
    }




}
