package com.plate.hy.controller.MemberController

import com.plate.hy.service.MemberService
import com.plate.hy.controller.MemberController.dto.SignUpRequest
import com.plate.hy.controller.MemberController.dto.SignUpResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "유저 api")
@RestController
@RequestMapping("member")
class MemberController(
    private val memberService: MemberService
) {

    @Operation(summary = "회원가입")
    @PostMapping("/sign-up")
    fun signUp(@RequestBody signUpRequest: SignUpRequest): ResponseEntity<SignUpResponse> {
        val signUpMember = memberService.signUp(signUpRequest.name, signUpRequest.phoneNumber, signUpRequest.password)

        return ResponseEntity.ok().body(SignUpResponse(id = signUpMember.id, name = signUpMember.name))
    }

//    @Operation(summary = "로그인")
//    @PostMapping("/login")
//    fun login(@RequestBody signUpRequest: SignUpRequest): SignUpResponse {
//        memberService.signUp(signUpRequest.name, signUpRequest.phoneNumber, signUpRequest.password)
//
//        return
//    }
}
