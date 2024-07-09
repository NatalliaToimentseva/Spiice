package com.example.spiice.ui.logInScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.spiice.databinding.FragmentLogInBinding
import com.example.spiice.navigator.navigator
import com.example.spiice.repositoty.SharedPreferencesRepository
import com.example.spiice.ui.signUpScreen.SignUpFragment
import com.example.spiice.ui.notesListScreen.NotesListFragment
import com.example.spiice.utils.activateButton
import com.example.spiice.utils.emptyFieldValidation
import com.example.spiice.utils.fieldHandler
import com.example.spiice.utils.makeToast

class LogInFragment : Fragment() {

    private var binding: FragmentLogInBinding? = null
    private var isEmailValid = false
    private var isPasswordValid = false
    private val viewModel: LogInViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLogInBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.exceptions.observe(viewLifecycleOwner) { e ->
            if (e != null) e.message?.let { makeToast(requireActivity(), it) }
        }

        binding?.run {
            loginScreen.setOnClickListener {
                if (it.hasFocus()) {
                    emailLogLayout.clearFocus()
                    passwordLogLayout.clearFocus()
                }
            }
        }

        binding?.run {
            emailLogET.doAfterTextChanged {
                isEmailValid = fieldHandler(
                    emailLogET,
                    emailLogLayout,
                    emptyFieldValidation(it.toString())
                )
                logInButton.isEnabled = activateButton(isEmailValid, isPasswordValid)
            }
        }

        binding?.run {
            passwordLogET.doAfterTextChanged {
                isPasswordValid = fieldHandler(
                    passwordLogET,
                    passwordLogLayout,
                    emptyFieldValidation(it.toString())
                )
                logInButton.isEnabled = activateButton(isEmailValid, isPasswordValid)
            }
        }

        binding?.apply {
            logInButton.setOnClickListener {
                viewModel.getAccount(
                    emailLogET.text.toString(),
                    passwordLogET.text.toString()
                )?.let { account ->
                    if (SharedPreferencesRepository.isFirstLaunch()) SharedPreferencesRepository.setFirstLaunch()
                    SharedPreferencesRepository.setEmail(account.email)
                    navigator().startFragment(NotesListFragment.getFragment(account.email))
                }
            }
        }

        binding?.singUpFromLoginScreenButton?.setOnClickListener {
            navigator().cancelFragment()
            navigator().startFragment(SignUpFragment())
        }
    }
}