package com.example.spiice.ui.signUpScreen

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.spiice.App
import com.example.spiice.databinding.FragmentSignUpBinding
import com.example.spiice.di.ViewModelsProvider
import com.example.spiice.navigator.navigator
import com.example.spiice.repositoty.SharedPreferencesRepository
import com.example.spiice.ui.logInScreen.LogInFragment
import com.example.spiice.ui.navigationContainer.NavigationFragment
import com.example.spiice.utils.activateButton
import com.example.spiice.utils.clearFields
import com.example.spiice.utils.createSpanForView
import com.example.spiice.utils.emailValidator
import com.example.spiice.utils.fieldHandler
import com.example.spiice.utils.makeToast
import com.example.spiice.utils.nameValidator
import com.example.spiice.utils.passwordValidator
import javax.inject.Inject

class SignUpFragment : Fragment() {

    @Inject
    lateinit var sharedPreferencesRepository: SharedPreferencesRepository

    @Inject
    lateinit var viewModelsProvider: ViewModelsProvider

    private val viewModel: SignUpViewModel by viewModels { viewModelsProvider }

    private var isValidFirstName = false
    private var isValidLastName = false
    private var isValidEmail = false
    private var isValidPassword = false
    private var binding: FragmentSignUpBinding? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        App.appComponent?.inject(this)
    }

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
            if (e != null) e.message?.let {
                makeToast(requireActivity(), it)
                viewModel.clearException()
            }
        }
        viewModel.email.observe(viewLifecycleOwner) { emailData ->
            emailData?.let { email ->
                sharedPreferencesRepository.setEmail(email)
                binding?.let { binding ->
                    clearFields(
                        binding.emailSignUpET,
                        binding.firstNameSignUpET,
                        binding.lastNameSignUpET,
                        binding.passwordSignUpET
                    )
                }
                navigator().startFragment(NavigationFragment())
                viewModel.clearEmail()
            }
        }
        viewModel.progressBarVisibility.observe(viewLifecycleOwner) {
            binding?.progressBar?.visibility = if (it) View.VISIBLE else View.GONE
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
                viewModel.createAccount(
                    firstNameSignUpET.text.toString(),
                    lastNameSignUpET.text.toString(),
                    emailSignUpET.text.toString(),
                    passwordSignUpET.text.toString()
                )
                if (sharedPreferencesRepository.isFirstLaunch()) sharedPreferencesRepository.setFirstLaunch()
            }
        }

        binding?.let { binding ->
            binding.loginFromSignUpScreenButton.setOnClickListener {
                navigator().cancelFragment()
                navigator().startFragment(LogInFragment())
                clearFields(
                    binding.emailSignUpET,
                    binding.firstNameSignUpET,
                    binding.lastNameSignUpET,
                    binding.passwordSignUpET
                )
            }
        }
    }
}
