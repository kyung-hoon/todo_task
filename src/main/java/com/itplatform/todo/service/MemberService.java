package com.itplatform.todo.service;

import com.itplatform.todo.domain.Member;
import com.itplatform.todo.domain.Task;

public interface MemberService {

    Member save(Member member);
    Boolean isValidUserName(String memberId);
}
