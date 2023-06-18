package com.itplatform.todo.service;

import com.itplatform.todo.domain.Member;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class LoginServiceImpl implements LoginService{

    private MemberService memberService;
    public LoginServiceImpl(MemberService memberService) {
        this.memberService=memberService;
    }

    @Override
    public Member login(Member member) {
        Member user =memberService.findMemberByMemberId(member.getMemberId());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if(user==null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }else{
            if(encoder.matches(member.getPassword(), user.getPassword())){
                return user;
            }else {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
            }
        }
    }
}
