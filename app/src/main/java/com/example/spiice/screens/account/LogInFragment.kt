package com.example.spiice.screens.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import com.example.spiice.databinding.FragmentLogInBinding
import com.example.spiice.navigator
import com.example.spiice.screens.account.notes.screen.NotesListFragment
import com.example.spiice.validations.activateButton
import com.example.spiice.validations.emptyFieldValidation
import com.example.spiice.validations.fieldHandler

class LogInFragment : Fragment() {

    private var binding: FragmentLogInBinding? = null
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