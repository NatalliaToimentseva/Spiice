package com.example.spiice.ui.signUpScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spiice.models.accountModel.SignUpAccountData
import com.example.spiice.repositoty.RepositoryProvider
import com.example.spiice.roomDB.AccountAlreadyExistException
import com.example.spiice.roomDB.AppExceptions

class SignUpViewModel : ViewModel() {

    private val accountRepository = RepositoryProvider.getAccountRepository()

    private var _exceptions = MutableLiveData<AppExceptions?>(null)
    val exceptions get() = _exceptions

    private fun setException(e: AppExceptions?) {
        _exceptions.value = e
    }

    fun createAccount(
        firstName: String,
        lastName: String,
        email: String,
        password: String
    ): Boolean {
        var isSuccess = false
        try {
            accountRepository.createAccount(
                SignUpAccountData(
                    firstName, lastName, email, password.toCharArray()
                )
            )
            isSuccess = true
        } catch (e: AccountAlreadyExistException) {
            setException(e)
        }
        setException(null)
        return isSuccess
    }
}