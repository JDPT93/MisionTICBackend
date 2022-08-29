package com.jdpt93.cycle3.backend.converters;

import com.jdpt93.cycle3.backend.data.MatchData;
import com.jdpt93.cycle3.backend.entities.Match;

public class MatchConverter extends Converter<Match, MatchData> {

    private TeamConverter teamConverter = new TeamConverter();
    private UserConverter userConverter = new UserConverter();

    @Override
    public Match toEntity(MatchData object) {
        return Match.builder()
                .id(object.getId())
                .user(userConverter.toEntity(object.getUser()))
                .localTeam(teamConverter.toEntity(object.getLocalTeam()))
                .guestTeam(teamConverter.toEntity(object.getGuestTeam()))
                .date(object.getDate())
                .localGoals(object.getLocalGoals())
                .guestGoals(object.getGuestGoals())
                .build();
    }

    @Override
    public MatchData toData(Match object) {
        return MatchData.builder()
                .id(object.getId())
                .user(userConverter.toData(object.getUser()))
                .localTeam(teamConverter.toData(object.getLocalTeam()))
                .guestTeam(teamConverter.toData(object.getGuestTeam()))
                .date(object.getDate())
                .localGoals(object.getLocalGoals())
                .guestGoals(object.getGuestGoals())
                .build();
    }

}
