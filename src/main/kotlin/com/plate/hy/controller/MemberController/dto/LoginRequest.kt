package com.plate.hy.controller.MemberController.dto

import com.plate.hy.domain.Password
import com.plate.hy.domain.PhoneNumber
import com.plate.hy.service.member.MemberCommand
import io.swagger.v3.oas.annotations.media.Schema

data class LoginRequest (
    @Schema(description = "유저 휴대폰 번호", example = "01011112222")
    val phoneNumber: String,
    @Schema(description = "유저 비밀번호", example = "1234pass")
    val password: String
) {
    fun toCommand() : MemberCommand.Login = MemberCommand.Login(
        phoneNumber = PhoneNumber(this.phoneNumber),
        password = Password(this.password)
    )
}
