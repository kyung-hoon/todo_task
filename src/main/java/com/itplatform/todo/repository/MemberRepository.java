package com.itplatform.todo.repository;


import com.itplatform.todo.domain.Member;
import com.itplatform.todo.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer>  {

    public Boolean existsByMemberId(String memberId);
    public Member findByMemberId(String memberId);
}
