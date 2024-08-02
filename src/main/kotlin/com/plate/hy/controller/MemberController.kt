package com.plate.hy.controller

import io.swagger.v3.oas.annotations.Operation
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import io.swagger.v3.oas.annotations.tags.Tag

@Tag(name = "유저 api")
@Controller
class MemberController {

    @Operation(summary = "test")
    @GetMapping("/test")
    fun getHello(): String {
        return "Hello, base-project"
    }
}
