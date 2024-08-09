package com.plate.hy.controller.MemberController.dto

import io.swagger.v3.oas.annotations.media.Schema

data class SignUpResponse (
    @Schema(description = "유저 index", example = "1")
    val id: Long,
    @Schema(description = "유저 이름", example = "정휘영")
    val name: String,
)
