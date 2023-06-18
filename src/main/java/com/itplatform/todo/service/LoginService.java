package com.itplatform.todo.service;

import com.itplatform.todo.domain.Member;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {
    Member login(Member member);
}
