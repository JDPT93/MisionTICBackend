package com.jdpt93.cycle3.backend.converters;

import com.jdpt93.cycle3.backend.data.TeamData;
import com.jdpt93.cycle3.backend.entities.Team;

public class TeamConverter extends Converter<Team, TeamData> {

    @Override
    public Team toEntity(TeamData object) {
        return Team.builder()
                .id(object.getId())
                .name(object.getName())
                .build();
    }

    @Override
    public TeamData toData(Team object) {
        return TeamData.builder()
                .id(object.getId())
                .name(object.getName())
                .build();
    }

}
