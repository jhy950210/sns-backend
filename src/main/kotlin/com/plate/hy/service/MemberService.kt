package com.plate.hy.service

import com.plate.hy.domain.Member
import com.plate.hy.domain.Password
import com.plate.hy.domain.PhoneNumber
import com.plate.hy.repository.MemberRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class MemberService(
    private val memberRepository: MemberRepository,
    private val passwordEncoder: PasswordEncoder
) {

    fun signUp(name: String, phoneNumber: String, password: String): Member {
        val createdPhoneNumber = PhoneNumber(phoneNumber).value
        val createdPassword = Password(password).value

        memberRepository.findByPhoneNumber(createdPassword)?.let {
            throw IllegalArgumentException("이미 가입된 휴대폰 번호 입니다.")
        }

        val member = Member.create(name, createdPhoneNumber, createdPassword, passwordEncoder)

        return memberRepository.save(member)
    }

}
