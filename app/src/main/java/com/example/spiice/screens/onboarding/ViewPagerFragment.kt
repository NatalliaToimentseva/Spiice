package com.example.spiice.screens.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.spiice.databinding.FragmentViewPagerBinding

class ViewPagerFragment : Fragment() {

    private var binding: FragmentViewPagerBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentViewPagerBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = NoteViewPagerAdapter(this)
        binding?.run {
            viewPager.adapter = adapter
            indicator.setViewPager(viewPager)
            adapter.registerAdapterDataObserver(indicator.adapterDataObserver)
        }
    }
}