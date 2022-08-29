package com.jdpt93.cycle3.backend.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "usuarios")
public class User {

    @Id
    @Column(length = 4, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 100, name = "nombre", nullable = false)
    private String fullname;

    @Column(length = 50, name = "correo", nullable = false, unique = true)
    private String email;

    @Column(length = 10, name = "username", nullable = false, unique = true)
    private String nickname;

    @Column(length = 10, nullable = false)
    @Getter(value = AccessLevel.NONE)
    private String password;

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

}
