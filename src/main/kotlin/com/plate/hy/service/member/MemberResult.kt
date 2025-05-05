package com.plate.hy.service.member

class MemberResult {
    data class Info(
        val id: Long,
        val name: String,
        val phoneNumber: String,
    )

    data class Login(
        val accessToken: String
    )
}