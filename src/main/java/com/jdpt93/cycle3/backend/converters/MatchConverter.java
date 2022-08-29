package com.jdpt93.cycle3.backend.converters;

import com.jdpt93.cycle3.backend.data.MatchData;
import com.jdpt93.cycle3.backend.entities.Match;

public class MatchConverter extends Converter<Match, MatchData> {

    @Override
    public Match toEntity(MatchData object) {
        return Match.builder()
                .id(object.getId())
                .user(object.getUser())
                .localTeam(object.getLocalTeam())
                .localTeam(object.getGuestTeam())
                .date(object.getDate())
                .localGoals(object.getLocalGoals())
                .localGoals(object.getGuestGoals())
                .build();
    }

    @Override
    public MatchData toData(Match object) {
        return MatchData.builder()
                .id(object.getId())
                .user(object.getUser())
                .localTeam(object.getLocalTeam())
                .localTeam(object.getGuestTeam())
                .date(object.getDate())
                .localGoals(object.getLocalGoals())
                .localGoals(object.getGuestGoals())
                .build();
    }

}
