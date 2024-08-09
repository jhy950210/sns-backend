package com.plate.hy.controller.MemberController.dto

import io.swagger.v3.oas.annotations.media.Schema

data class SignUpRequest (
    @Schema(description = "유저 이름", example = "정휘영")
    val name: String,
    @Schema(description = "유저 휴대폰 번호", example = "01011112222")
    val phoneNumber: String,
    @Schema(description = "유저 비밀번호", example = "1234pass")
    val password: String
)
