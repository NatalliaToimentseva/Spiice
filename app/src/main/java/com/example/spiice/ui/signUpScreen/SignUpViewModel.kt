package com.example.spiice.ui.signUpScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spiice.models.accountModel.SignUpAccountData
import com.example.spiice.repositoty.AccountRepository
import com.example.spiice.roomDB.AccountAlreadyExistException
import com.example.spiice.roomDB.AppExceptions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor (
    private val accountRepository: AccountRepository
) : ViewModel() {

    private var _exceptions = MutableLiveData<AppExceptions?>(null)
    val exceptions get() = _exceptions

    private var _email = MutableLiveData<String?>(null)
    val email get() = _email

    private var _progressBarVisibility = MutableLiveData(false)
    val progressBarVisibility get() = _progressBarVisibility

    private fun setException(e: AppExceptions?) {
        _exceptions.postValue(e)
    }

    fun clearException() {
        _exceptions.value = null
    }

    fun clearEmail() {
        _email.value = null
    }

    fun createAccount(
        firstName: String,
        lastName: String,
        email: String,
        password: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _progressBarVisibility.postValue(true)
                accountRepository.createAccount(
                    SignUpAccountData(
                        firstName, lastName, email, password.toCharArray()
                    )
                )
                _progressBarVisibility.postValue(false)
                _email.postValue(email)
            } catch (e: AccountAlreadyExistException) {
                setException(e)
            }
        }
    }
}