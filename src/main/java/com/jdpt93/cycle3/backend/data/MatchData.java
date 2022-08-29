package com.jdpt93.cycle3.backend.data;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.jdpt93.cycle3.backend.entities.Team;
import com.jdpt93.cycle3.backend.entities.User;

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
public class MatchData {

    private int id;

    @NotNull
    private User user;

    @NotNull
    private Team localTeam;

    @NotNull
    private Team guestTeam;

    @NotNull
    private Date date;

    private int localGoals;

    private int guestGoals;

}
