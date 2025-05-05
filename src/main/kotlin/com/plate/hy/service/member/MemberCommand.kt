package com.plate.hy.service.member

import com.plate.hy.domain.Password
import com.plate.hy.domain.PhoneNumber

class MemberCommand {
    data class Login(
        val phoneNumber: PhoneNumber,
        val password: Password
    )
}