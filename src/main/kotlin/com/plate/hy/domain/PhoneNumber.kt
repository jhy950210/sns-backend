package com.plate.hy.domain

import java.util.regex.Pattern

class PhoneNumber(val value: String) {
    init {
        require(isValidPattern(value)) { "Invalid phone number format" }
    }

    companion object {
        private  val PATTERN: Pattern = Pattern.compile("^\\d{10,11}$")

        private fun isValidPattern(number: String): Boolean = PATTERN.matcher(number).matches()
    }
}
