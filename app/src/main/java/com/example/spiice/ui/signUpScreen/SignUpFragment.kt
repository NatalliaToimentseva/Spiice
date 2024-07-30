package com.example.spiice.ui.signUpScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.spiice.databinding.FragmentSignUpBinding
import com.example.spiice.navigator.navigator
import com.example.spiice.repositoty.SharedPreferencesRepository
import com.example.spiice.ui.logInScreen.LogInFragment
import com.example.spiice.ui.notesListScreen.NotesListFragment
import com.example.spiice.utils.createSpanForView
import com.example.spiice.utils.activateButton
import com.example.spiice.utils.emailValidator
import com.example.spiice.utils.fieldHandler
import com.example.spiice.utils.makeToast
import com.example.spiice.utils.nameValidator
import com.example.spiice.utils.passwordValidator

class SignUpFragment : Fragment() {

    private var isValidFirstName = false
    private var isValidLastName = false
    private var isValidEmail = false
    private var isValidPassword = false
    private var binding: FragmentSignUpBinding? = null
    private val viewModel: SignUpViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.exceptions.observe(viewLifecycleOwner) { e ->
            if (e != null) e.message?.let { makeToast(requireActivity(), it) }
        }

        binding?.loginFromSignUpScreenButton?.let { createSpanForView(it) }

        binding?.run {
            signUpScreen.setOnClickListener {
                if (it.hasFocus()) {
                    firstNameSingUpLayout.clearFocus()
                    lastNameSignUpLayout.clearFocus()
                    emailSignUpLayout.clearFocus()
                    passwordSignUpLayout.clearFocus()
                }
            }
        }

        binding?.run {
            firstNameSignUpET.doAfterTextChanged {
                isValidFirstName = fieldHandler(
                    firstNameSignUpET,
                    firstNameSingUpLayout,
                    nameValidator(it.toString().trim())
                )
                signUpButton.isEnabled =
                    activateButton(isValidFirstName, isValidLastName, isValidEmail, isValidPassword)
            }
        }

        binding?.run {
            lastNameSignUpET.doAfterTextChanged {
                isValidLastName = fieldHandler(
                    lastNameSignUpET,
                    lastNameSignUpLayout,
                    nameValidator(it.toString().trim())
                )
                signUpButton.isEnabled =
                    activateButton(isValidFirstName, isValidLastName, isValidEmail, isValidPassword)
            }
        }

        binding?.run {
            emailSignUpET.doAfterTextChanged {
                isValidEmail = fieldHandler(
                    emailSignUpET,
                    emailSignUpLayout,
                    emailValidator(it.toString().trim())
                )
                signUpButton.isEnabled =
                    activateButton(isValidFirstName, isValidLastName, isValidEmail, isValidPassword)
            }
        }

        binding?.run {
            passwordSignUpET.doAfterTextChanged {
                isValidPassword = fieldHandler(
                    passwordSignUpET,
                    passwordSignUpLayout,
                    passwordValidator(it.toString().trim())
                )
                signUpButton.isEnabled =
                    activateButton(isValidFirstName, isValidLastName, isValidEmail, isValidPassword)
            }
        }

        binding?.apply {
            signUpButton.setOnClickListener {
                if (viewModel.createAccount(
                        firstNameSignUpET.text.toString(),
                        lastNameSignUpET.text.toString(),
                        emailSignUpET.text.toString(),
                        passwordSignUpET.text.toString()
                    )
                ) {
                    if (SharedPreferencesRepository.isFirstLaunch()) SharedPreferencesRepository.setFirstLaunch()
                    SharedPreferencesRepository.setEmail(emailSignUpET.text.toString())
                    navigator().startFragment(NotesListFragment.getFragment(emailSignUpET.text.toString()))
                }
            }
        }

        binding?.loginFromSignUpScreenButton?.setOnClickListener {
            navigator().cancelFragment()
            navigator().startFragment(LogInFragment())
        }
    }
}