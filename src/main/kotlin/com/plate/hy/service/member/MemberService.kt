package com.plate.hy.service.member

import com.plate.hy.entity.Member
import com.plate.hy.domain.Password
import com.plate.hy.domain.PhoneNumber
import com.plate.hy.repository.MemberRepository
import com.plate.hy.security.TokenProvider
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class MemberService(
    private val memberRepository: MemberRepository,
    private val passwordEncoder: PasswordEncoder,
    private val tokenProvider: TokenProvider
) {

    fun signUp(name: String, phoneNumber: String, password: String): MemberResult.Info {
        val createdPhoneNumber = PhoneNumber(phoneNumber).value
        val createdPassword = Password(password).value

        memberRepository.findByPhoneNumber(createdPassword)?.let {
            throw IllegalArgumentException("이미 가입된 휴대폰 번호 입니다.")
        }

        val member = Member.create(name, createdPhoneNumber, createdPassword, passwordEncoder)
        val savedMember = memberRepository.save(member)

        return MemberResult.Info(
            id = savedMember.id,
            name = savedMember.name,
            phoneNumber = savedMember.phoneNumber,
        )
    }

    fun login(command: MemberCommand.Login): MemberResult.Login {
        val foundMember = memberRepository.findByPhoneNumber(command.phoneNumber.value)
            ?: throw IllegalArgumentException("가입된 휴대폰번호가 아닙니다.")

        val isMatchedPassword = passwordEncoder.matches(command.password.value, foundMember.password)
        if (!isMatchedPassword) throw IllegalArgumentException("잘못된 패스워드입니다.")

        return MemberResult.Login(
            accessToken = tokenProvider.createToken(foundMember.id)
        )

    }

}
