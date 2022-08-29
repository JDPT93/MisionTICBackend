package com.jdpt93.cycle3.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jdpt93.cycle3.backend.entities.Match;

@Repository
public interface MatchRepository extends JpaRepository<Match, Integer> {

}
