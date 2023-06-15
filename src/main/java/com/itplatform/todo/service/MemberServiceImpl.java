package com.itplatform.todo.service;

import com.itplatform.todo.domain.Member;
import com.itplatform.todo.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService{
    private MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository){
        this.memberRepository=memberRepository;
    }
    @Override
    public Member save(Member member) {
        return memberRepository.save(member);
    }
    @Override
    public Boolean isValidUserName(String memberId){
        return !memberRepository.existsByMemberId(memberId);
    }
}
