package com.itplatform.todo.controller;


import com.itplatform.todo.domain.Member;
import com.itplatform.todo.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
@Tag(name = "Login", description = "Login API")
@RestController
@RequestMapping("/api/login")
public class LoginController extends AbstractApiController {
    private LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }
    @Operation(summary = "로그인", description = "사용자가 입력한 ID/PW를 통해 로그인읋 한다")
    @PostMapping()
    public ResponseEntity<Member> login(@RequestBody Member member) {

        return ok(loginService.login(member));
    }
}
