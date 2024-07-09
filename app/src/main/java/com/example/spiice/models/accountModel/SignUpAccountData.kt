package com.example.spiice.models.accountModel

data class SignUpAccountData(
    val name: String,
    val surname: String,
    val email: String,
    val password: CharArray,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SignUpAccountData

        if (name != other.name) return false
        if (surname != other.surname) return false
        if (email != other.email) return false
        if (!password.contentEquals(other.password)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + surname.hashCode()
        result = 31 * result + email.hashCode()
        result = 31 * result + password.contentHashCode()
        return result
    }
}
