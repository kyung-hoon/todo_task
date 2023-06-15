package com.itplatform.todo.controller;


import com.itplatform.todo.domain.Member;
import com.itplatform.todo.domain.Task;
import com.itplatform.todo.service.MemberService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/member")
public class MemberController {
    private MemberService memberService;

    public MemberController(MemberService memberService){this.memberService=memberService;}


    @PostMapping()
    public Member save(@RequestBody Member member) {
        if(!memberService.isValidUserName(member.getMemberId())){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        return memberService.save(member);
    }

}
