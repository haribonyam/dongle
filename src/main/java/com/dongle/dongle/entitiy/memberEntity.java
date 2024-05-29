package com.dongle.dongle.entitiy;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="member")
public class memberEntity {
    @Id
    private Long id;
    @Column(name="email",unique = true)
    private String email;
    @Column(name="password")
    private String password;
    @Column(name="nickname",unique = true)
    private String nickname;
    @Column(name="role")
    private String role;
    @Column(name="town")
    private String town;
}
