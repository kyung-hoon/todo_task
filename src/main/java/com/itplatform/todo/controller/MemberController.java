package com.itplatform.todo.controller;


import com.itplatform.todo.domain.Member;
import com.itplatform.todo.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/member")
public class MemberController extends AbstractApiController{
    private MemberService memberService;

    public MemberController(MemberService memberService){this.memberService=memberService;}


    @PostMapping()
    public ResponseEntity<Member> save(@RequestBody Member member) {
        if(!memberService.isValidUserName(member.getMemberId())){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        return ok(memberService.save(member));
    }

    @GetMapping()
    public  ResponseEntity<List<Member>> getMembers(){
        return ok(memberService.findAll());
    }

    @PostMapping("/{id}")
    public  ResponseEntity<Member> update(@RequestBody Member member){
        return ok(memberService.update(member));
    }
}
