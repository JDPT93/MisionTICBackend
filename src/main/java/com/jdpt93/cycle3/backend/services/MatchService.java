package com.jdpt93.cycle3.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return matchConverter.toData(matchRepository.save(matchConverter.toEntity(match)));
    }

}
