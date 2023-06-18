package com.itplatform.todo.service;

import com.itplatform.todo.domain.Member;
import com.itplatform.todo.domain.Task;
import com.itplatform.todo.repository.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService{
    private MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository){
        this.memberRepository=memberRepository;
    }
    @Override
    public Member save(Member member) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        return memberRepository.save(member);
    }
    @Override
    public Boolean isValidUserName(String memberId){
        return !memberRepository.existsByMemberId(memberId);
    }

    @Override
    public Member findMemberByMemberId(String memberId) {
        return memberRepository.findByMemberId(memberId);
    }

    @Override
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @Override
    @Transactional
    public Member update(Member member) {
        if (!memberRepository.existsByMemberId(member.getMemberId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not updated");
        }
        Member updatedMember = memberRepository.findByMemberId(member.getMemberId());
        updatedMember.setRoleType(member.getRoleType());
        return memberRepository.save(updatedMember);
    }
}
