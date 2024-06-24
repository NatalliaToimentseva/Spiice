package com.example.spiice.ui.logInScreen

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import com.example.spiice.databinding.FragmentLogInBinding
import com.example.spiice.navigator.navigator
import com.example.spiice.ui.signUpScreen.SignUpFragment
import com.example.spiice.ui.notesListScreen.NotesListFragment
import com.example.spiice.utils.activateButton
import com.example.spiice.utils.emptyFieldValidation
import com.example.spiice.utils.fieldHandler

class LogInFragment : Fragment() {

    private var binding: FragmentLogInBinding? = null
    var preferences : SharedPreferences? = null
    private var isEmailValid = false
    private var isPasswordValid = false

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

        binding?.logInButton?.setOnClickListener {
            navigator().startFragment(NotesListFragment())
        }

        binding?.singUpFromLoginScreenButton?.setOnClickListener {
            navigator().cancelFragment()
            navigator().startFragment(SignUpFragment())
        }
    }
}