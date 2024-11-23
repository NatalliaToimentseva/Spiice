package com.example.spiice.ui.logInScreen

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.spiice.App
import com.example.spiice.databinding.FragmentLogInBinding
import com.example.spiice.di.ViewModelsProvider
import com.example.spiice.navigator.navigator
import com.example.spiice.repositoty.SharedPreferencesRepository
import com.example.spiice.ui.navigationContainer.NavigationFragment
import com.example.spiice.ui.signUpScreen.SignUpFragment
import com.example.spiice.utils.activateButton
import com.example.spiice.utils.clearFields
import com.example.spiice.utils.emptyFieldValidation
import com.example.spiice.utils.fieldHandler
import com.example.spiice.utils.makeToast
import javax.inject.Inject

class LogInFragment : Fragment() {

    @Inject
    lateinit var sharedPreferencesRepository: SharedPreferencesRepository

    @Inject
    lateinit var viewModelsProvider: ViewModelsProvider

    private val viewModel: LogInViewModel by viewModels { viewModelsProvider }

    private var binding: FragmentLogInBinding? = null
    private var isEmailValid = false
    private var isPasswordValid = false

    override fun onAttach(context: Context) {
        super.onAttach(context)
        App.appComponent?.inject(this)
    }

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
            if (e != null) e.message?.let {
                makeToast(requireActivity(), it)
                viewModel.clearException()
            }
        }
        viewModel.email.observe(viewLifecycleOwner) { emailData ->
            emailData?.let { email ->
                sharedPreferencesRepository.setEmail(email)
                navigator().startFragment(NavigationFragment())
                binding?.let { binding ->
                    clearFields(
                        binding.emailLogET,
                        binding.passwordLogET
                    )
                }
                viewModel.clearEmail()
            }
        }
        viewModel.progressBarVisibility.observe(viewLifecycleOwner) {
            binding?.progressBar?.visibility = if (it) View.VISIBLE else View.GONE
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
                viewModel.getEmail(
                    emailLogET.text.toString(),
                    passwordLogET.text.toString()
                )
                if (sharedPreferencesRepository.isFirstLaunch()) sharedPreferencesRepository.setFirstLaunch()
            }
        }

        binding?.let { binding ->
            binding.singUpFromLoginScreenButton.setOnClickListener {
                navigator().cancelFragment()
                navigator().startFragment(SignUpFragment())
                clearFields(
                    binding.emailLogET,
                    binding.passwordLogET
                )
            }
        }
    }
}