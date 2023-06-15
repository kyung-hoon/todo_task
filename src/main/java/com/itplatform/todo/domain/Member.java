package com.itplatform.todo.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "Member")
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


    @NotBlank
    @Column(name = "role_type")
    private String role;


    public int getId() {
        return id;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
}
