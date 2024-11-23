package com.example.spiice.ui.navigationContainer

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.spiice.App
import com.example.spiice.R
import com.example.spiice.databinding.FragmentNavigationContainerBinding
import com.example.spiice.repositoty.SharedPreferencesRepository
import com.example.spiice.ui.addNoteScreen.AddNoteFragment
import com.example.spiice.ui.notesListScreen.NotesListFragment
import com.example.spiice.ui.profileScreen.ProfileFragment
import com.example.spiice.ui.searchScreen.SearchFragment
import javax.inject.Inject

class NavigationFragment : Fragment() {

    @Inject
    lateinit var sharedPreferencesRepository: SharedPreferencesRepository
    private var binding: FragmentNavigationContainerBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNavigationContainerBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        App.appComponent?.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null) {
            startFragment(NotesListFragment())
        }

        binding?.bottomNavigation?.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home_page -> {
                    startFragment(NotesListFragment())
                    true
                }

                R.id.search_page -> {
                    startFragment(SearchFragment())
                    true
                }

                R.id.add_note_page -> {
                    startFragment(AddNoteFragment())
                    true
                }

                R.id.profile_page -> {
                    startFragment(ProfileFragment())
                    true
                }

                else -> false
            }
        }
    }

    private fun startFragment(fragment: Fragment) {
        childFragmentManager.beginTransaction()
            .replace(R.id.nav_container, fragment)
            .commit()
    }
}