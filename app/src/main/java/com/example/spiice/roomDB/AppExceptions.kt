package com.example.spiice.roomDB

open class AppExceptions(message: String) : RuntimeException(message)

class AuthException(message: String) : AppExceptions(message)

class AccountAlreadyExistException(message: String) : AppExceptions(message)

class PasswordMismatchException(message: String) : AppExceptions(message)