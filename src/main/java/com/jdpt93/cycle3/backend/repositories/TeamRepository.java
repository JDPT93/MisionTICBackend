package com.jdpt93.cycle3.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jdpt93.cycle3.backend.entities.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {

    public Optional<Team> findByName(String nickname);

}
