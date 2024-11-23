package com.example.spiice.ui.profileScreen

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.spiice.App
import com.example.spiice.R
import com.example.spiice.databinding.FragmentProfileBinding
import com.example.spiice.di.ViewModelsProvider
import com.example.spiice.navigator.navigator
import com.example.spiice.repositoty.SharedPreferencesRepository
import com.example.spiice.roomDB.entities.getFullName
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import javax.inject.Inject

private const val NO_NAME = "No name"

class ProfileFragment : Fragment() {

    @Inject
    lateinit var sharedPreferencesRepository: SharedPreferencesRepository

    @Inject
    lateinit var viewModelsProvider: ViewModelsProvider

    private val viewModel: ProfileViewModel by viewModels { viewModelsProvider }

    private var binding: FragmentProfileBinding? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        App.appComponent?.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userEmail = sharedPreferencesRepository.getEmail() ?: ""
        viewModel.getUserFullName(userEmail)
        viewModel.getNotesCount(userEmail)
        viewModel.userFullName.observe(viewLifecycleOwner) { userName ->
            binding?.userName?.text = userName?.getFullName() ?: NO_NAME
        }
        viewModel.userNote.observe(viewLifecycleOwner) { count ->
            binding?.userNotes?.text = getString(R.string.user_notes, count)
        }
        viewModel.isDeleted.observe(viewLifecycleOwner) { deleted ->
            if (deleted) {
                sharedPreferencesRepository.clearUserData()
                navigator().logout()
            }
        }

        binding?.deleteAccount?.setOnClickListener {
            sharedPreferencesRepository.getEmail()?.let { email ->
                showDeleteDialog(
                    getString(R.string.delete_account_title),
                    getString(R.string.delete_account_message),
                ) { _, _ ->
                    viewModel.deleteAccount(email)
                }
            }
        }

        binding?.deleteNotes?.setOnClickListener {
            sharedPreferencesRepository.getEmail()?.let { email ->
                showDeleteDialog(
                    getString(R.string.delete_notes_title),
                    getString(R.string.delete_notes_message)
                ) { _, _ ->
                    viewModel.deleteAllUserNotes(email)
                }
            }
        }

        binding?.logout?.setOnClickListener {
            sharedPreferencesRepository.clearUserData()
            navigator().logout()
        }
    }

    private fun showDeleteDialog(
        title: String,
        message: String,
        listener: DialogInterface.OnClickListener
    ) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(getString(R.string.positive_button), listener)
            .setNegativeButton(getString(R.string.negative_button)) { _, _ -> }
            .create()
            .show()
    }
}