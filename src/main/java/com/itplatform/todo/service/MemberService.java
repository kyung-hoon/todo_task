package com.itplatform.todo.service;

import com.itplatform.todo.domain.Member;
import com.itplatform.todo.domain.Task;

import java.util.List;

public interface MemberService {

    Member save(Member member);
    Boolean isValidUserName(String memberId);

    Member findMemberByMemberId(String MemberId);
    Member update(Member member);
    List<Member> findAll();
}
