package com.plate.hy.domain

class Password(val value: String) {
    init {
        require(isValid(value)) { "Invalid password" }
    }

    companion object {
        private const val MIN_LENGTH = 8
        private const val MAX_LENGTH = 20
        private val PASSWORD_PATTERN = Regex("^(?=.*[a-zA-Z])(?=.*\\d).*$")

        private fun isValid(password: String): Boolean {
            return password.length in MIN_LENGTH..MAX_LENGTH && PASSWORD_PATTERN.matches(password)
        }
    }
}
