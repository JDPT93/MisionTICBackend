package com.jdpt93.cycle3.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.jdpt93.cycle3.backend.converters.TeamConverter;
import com.jdpt93.cycle3.backend.data.TeamData;
import com.jdpt93.cycle3.backend.repositories.TeamRepository;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    private TeamConverter teamConverter = new TeamConverter();

    public List<TeamData> findAll() {
        return teamConverter.toData(teamRepository.findAll());
    }

    public TeamData save(TeamData team) {
        if (teamRepository.findByName(team.getName()).isPresent())
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Team already exists");
        return teamConverter.toData(teamRepository.save(teamConverter.toEntity(team)));
    }

}
