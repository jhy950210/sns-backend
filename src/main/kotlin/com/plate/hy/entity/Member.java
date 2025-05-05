package com.plate.hy.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Table(name = "members")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(name = "member_name", nullable = false)
    private String name;

    @Column(name = "member_phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "member_password", nullable = false)
    private String password;

    public static Member create(String name, String phoneNumber, String password, PasswordEncoder passwordEncoder) {
        return new Member(name, phoneNumber, passwordEncoder.encode(password));
    }

    private Member(String name, String phoneNumber, String password) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }
}
