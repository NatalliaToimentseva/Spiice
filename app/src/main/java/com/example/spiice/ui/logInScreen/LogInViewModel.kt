package com.example.spiice.ui.logInScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spiice.repositoty.RepositoryProvider
import com.example.spiice.roomDB.AppExceptions
import com.example.spiice.roomDB.AuthException
import com.example.spiice.roomDB.PasswordMismatchException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LogInViewModel : ViewModel() {

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

    fun getEmail(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _email.postValue(accountRepository.getAccount(email, password))
            } catch (e: AuthException) {
                setException(e)
            } catch (e: PasswordMismatchException) {
                setException(e)
            }
        }
    }
}