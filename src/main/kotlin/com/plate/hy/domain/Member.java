package com.plate.hy.domain;

import com.plate.hy.domain.constants.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Table(name = "members")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", nullable = false)
    private Long id;

    @Column(name = "member_name", nullable = false)
    private String name;

    @Column(name = "member_phone_number")
    private String phoneNumber;

    @Column(name = "member_email", nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "member_role", nullable = false)
    private Role role;

    @Column(name = "member_password")
    private String password;

    public static Member createUser(String name, String phoneNumber, String password, String email, PasswordEncoder passwordEncoder) {
        return new Member(name, phoneNumber, passwordEncoder.encode(password), email, Role.USER);
    }

    public static Member createGoogleUser(String name, String email) {
        return new Member(name, null, null, email, Role.USER);
    }

    public static Member createAdmin(String name, String phoneNumber, String password, String email, PasswordEncoder passwordEncoder) {
        return new Member(name, phoneNumber, passwordEncoder.encode(password), email, Role.ADMIN);
    }

    private Member(String name, String phoneNumber, String password, String email, Role role) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public Member update(String name) {
        //Todo: implement
        return null;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }
}
