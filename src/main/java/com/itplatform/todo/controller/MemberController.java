package com.itplatform.todo.controller;


import com.itplatform.todo.domain.Member;
import com.itplatform.todo.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@Tag(name = "Member", description = "Member API")
@RestController
@RequestMapping("/api/member")
public class MemberController extends AbstractApiController{
    private MemberService memberService;

    public MemberController(MemberService memberService){this.memberService=memberService;}

    @Operation(summary = "회원가입", description = "사용자가 입력한 ID/PW를 통해 회원가입을 한다")
    @PostMapping()
    public ResponseEntity<Member> save(@RequestBody Member member) {
        if(!memberService.isValidUserName(member.getMemberId())){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        return ok(memberService.save(member));
    }

    @Operation(summary = "전체 유저 조회", description = "전체 유저 목록을 반환한다.")
    @GetMapping()
    public  ResponseEntity<List<Member>> getMembers(){
        return ok(memberService.findAll());
    }

    @Operation(summary = "유저 업데이트", description = "유저를 업데이트 한다 (권한 변경시 사용)")
    @PostMapping("/{id}")
    public  ResponseEntity<Member> update(@RequestBody Member member){
        return ok(memberService.update(member));
    }
}
