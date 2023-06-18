package com.itplatform.todo.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "member")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Member {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @NotBlank
    @Column(name = "member_id")
    private String memberId;

    @NotBlank
    @Column(name = "password")
    private String password;


    @Enumerated(EnumType.STRING)
    @Column(name = "role_type")
    private MemberRole roleType =MemberRole.NORMAL;




    public int getId() {
        return id;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getPassword() {
        return password;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public MemberRole getRoleType() {
        return roleType;
    }

    public void setRoleType(MemberRole role) {
        this.roleType = role;
    }
}
