package com.jdpt93.cycle3.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jdpt93.cycle3.backend.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    public Optional<User> findByNickname(String nickname);

    public Optional<User> findByEmail(String email);

}
