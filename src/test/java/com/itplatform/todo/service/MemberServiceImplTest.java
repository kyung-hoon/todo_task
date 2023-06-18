package com.itplatform.todo.service;

import com.itplatform.todo.domain.Member;
import com.itplatform.todo.domain.MemberRole;
import com.itplatform.todo.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MemberServiceImplTest {

    @Mock
    private MemberRepository memberRepository;

    private MemberServiceImpl memberService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        memberService = new MemberServiceImpl(memberRepository);
    }

    @Test
    void save_ValidMember_ShouldReturnSavedMember() {
        Member member = new Member();
        member.setMemberId("testMemberId");
        member.setPassword("testPassword");

        when(memberRepository.save(member)).thenReturn(member);

        Member result = memberService.save(member);

        assertNotNull(result);
        assertEquals(member.getMemberId(), result.getMemberId());

        verify(memberRepository, times(1)).save(member);
    }

    @Test
    void isValidUserName_ExistingMemberId_ShouldReturnFalse() {
        String memberId = "existingMemberId";

        when(memberRepository.existsByMemberId(memberId)).thenReturn(true);

        Boolean result = memberService.isValidUserName(memberId);

        assertFalse(result);
        verify(memberRepository, times(1)).existsByMemberId(memberId);
    }

    @Test
    void isValidUserName_NonExistingMemberId_ShouldReturnTrue() {
        String memberId = "nonExistingMemberId";

        when(memberRepository.existsByMemberId(memberId)).thenReturn(false);

        Boolean result = memberService.isValidUserName(memberId);

        assertTrue(result);
        verify(memberRepository, times(1)).existsByMemberId(memberId);
    }

    @Test
    void findMemberByMemberId_ExistingMemberId_ShouldReturnMember() {
        String memberId = "existingMemberId";
        Member member = new Member();
        member.setMemberId(memberId);

        when(memberRepository.findByMemberId(memberId)).thenReturn(member);

        Member result = memberService.findMemberByMemberId(memberId);

        assertNotNull(result);
        assertEquals(memberId, result.getMemberId());
        verify(memberRepository, times(1)).findByMemberId(memberId);
    }

    @Test
    void findMemberByMemberId_NonExistingMemberId_ShouldReturnNull() {
        String memberId = "nonExistingMemberId";

        when(memberRepository.findByMemberId(memberId)).thenReturn(null);

        Member result = memberService.findMemberByMemberId(memberId);

        assertNull(result);
        verify(memberRepository, times(1)).findByMemberId(memberId);
    }

    @Test
    void findAll_ShouldReturnMemberList() {
        List<Member> members = new ArrayList<>();
        members.add(new Member());
        members.add(new Member());

        when(memberRepository.findAll()).thenReturn(members);

        List<Member> result = memberService.findAll();

        assertNotNull(result);
        assertEquals(members.size(), result.size());
        verify(memberRepository, times(1)).findAll();
    }

    @Test
    void update_ExistingMember_ShouldReturnUpdatedMember() {
        String memberId = "existingMemberId";

        Member existingMember = new Member();
        existingMember.setMemberId(memberId);

        Member updatedMember = new Member();
        updatedMember.setMemberId(memberId);
        updatedMember.setRoleType(MemberRole.ADMIN);

        when(memberRepository.existsByMemberId(memberId)).thenReturn(true);
        when(memberRepository.findByMemberId(memberId)).thenReturn(existingMember);
        when(memberRepository.save(existingMember)).thenReturn(updatedMember);

        Member result = memberService.update(updatedMember);

        assertNotNull(result);
        assertEquals(updatedMember.getRoleType(), result.getRoleType());
        verify(memberRepository, times(1)).existsByMemberId(memberId);
        verify(memberRepository, times(1)).findByMemberId(memberId);
        verify(memberRepository, times(1)).save(existingMember);
    }

    @Test
    void update_NonExistingMember_ShouldThrowResponseStatusException() {
        String memberId = "nonExistingMemberId";
        Member member = new Member();
        member.setMemberId(memberId);

        when(memberRepository.existsByMemberId(memberId)).thenReturn(false);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> memberService.update(member));
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());

        verify(memberRepository, times(1)).existsByMemberId(memberId);
        verify(memberRepository, never()).findByMemberId(memberId);
        verify(memberRepository, never()).save(any(Member.class));
    }
}