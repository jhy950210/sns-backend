package com.plate.hy

import com.plate.hy.domain.Member
import com.plate.hy.repository.MemberRepository
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class InitDB (
    private val memberRepository: MemberRepository,
    private val encoder: PasswordEncoder
): ApplicationRunner {
    override fun run(args: ApplicationArguments?) {
        memberRepository.save(Member.create("admin", "admin", encoder))
    }
}
