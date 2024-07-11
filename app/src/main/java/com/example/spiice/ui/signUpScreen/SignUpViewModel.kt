package com.example.spiice.ui.signUpScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spiice.models.accountModel.SignUpAccountData
import com.example.spiice.repositoty.RepositoryProvider
import com.example.spiice.roomDB.AccountAlreadyExistException
import com.example.spiice.roomDB.AppExceptions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {

    private val accountRepository = RepositoryProvider.getAccountRepository()

    private var _exceptions = MutableLiveData<AppExceptions?>(null)
    val exceptions get() = _exceptions

    private var _email = MutableLiveData<String?>(null)
    val email get() = _email

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
                accountRepository.createAccount(
                    SignUpAccountData(
                        firstName, lastName, email, password.toCharArray()
                    )
                )
                _email.postValue(email)
            } catch (e: AccountAlreadyExistException) {
                setException(e)
            }
        }
    }
}