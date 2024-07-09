package com.example.spiice.ui.logInScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spiice.models.accountModel.LoginAccountData
import com.example.spiice.repositoty.RepositoryProvider
import com.example.spiice.roomDB.AppExceptions
import com.example.spiice.roomDB.AuthException
import com.example.spiice.roomDB.PasswordMismatchException

class LogInViewModel : ViewModel() {

    private val accountRepository = RepositoryProvider.getAccountRepository()

    private var _exceptions = MutableLiveData<AppExceptions?>(null)
    val exceptions get() = _exceptions

    private fun setException(e: AppExceptions?) {
        _exceptions.value = e
    }

    fun getAccount(email: String, password: String): LoginAccountData? {
        var loginAccountData: LoginAccountData? = null
        try {
            loginAccountData = accountRepository.getAccount(email, password)
        } catch (e: AuthException) {
            setException(e)
        } catch (e: PasswordMismatchException){
            setException(e)
        }
        setException(null)
        return loginAccountData
    }
}