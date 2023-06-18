package com.itplatform.todo.service;

import com.itplatform.todo.domain.Member;
import com.itplatform.todo.domain.MemberRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoginServiceImplTest {

    @Mock
    private MemberService memberService;

    private LoginServiceImpl loginService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        loginService = new LoginServiceImpl(memberService);
    }


    @Test
    void login_InvalidMemberId_ShouldThrowResponseStatusException() {
        String invalidMemberId = "invalidMemberId";
        String password = "password";

        when(memberService.findMemberByMemberId(invalidMemberId)).thenReturn(null);
        Member member = new Member();
        member.setMemberId(invalidMemberId);
        member.setPassword(new BCryptPasswordEncoder().encode(password));
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () ->
                loginService.login(member));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());

        verify(memberService, times(1)).findMemberByMemberId(invalidMemberId);
    }

    @Test
    void login_InvalidPassword_ShouldThrowResponseStatusException() {
        String memberId = "admin";
        String validPassword = "1234";
        String invalidPassword = "invalidPassword";

        Member member = new Member();
        member.setMemberId(memberId);
        member.setPassword(new BCryptPasswordEncoder().encode(validPassword));

        when(memberService.findMemberByMemberId(memberId)).thenReturn(member);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () ->
                loginService.login(member));
        assertEquals(HttpStatus.UNAUTHORIZED, exception.getStatusCode());

        verify(memberService, times(1)).findMemberByMemberId(memberId);
    }
}
