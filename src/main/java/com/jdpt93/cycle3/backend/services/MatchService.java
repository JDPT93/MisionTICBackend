package com.jdpt93.cycle3.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.jdpt93.cycle3.backend.converters.MatchConverter;
import com.jdpt93.cycle3.backend.data.MatchData;
import com.jdpt93.cycle3.backend.repositories.MatchRepository;

@Service
public class MatchService {

    @Autowired
    private MatchRepository matchRepository;

    private MatchConverter matchConverter = new MatchConverter();

    public List<MatchData> findAll() {
        return matchConverter.toData(matchRepository.findAll());
    }

    public MatchData save(MatchData match) {
        if (match.getLocalTeam().getId() == match.getGuestTeam().getId())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Local Team and Guest Team must be different");
        return matchConverter.toData(matchRepository.save(matchConverter.toEntity(match)));
    }

}
