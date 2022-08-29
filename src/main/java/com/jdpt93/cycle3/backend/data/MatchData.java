package com.jdpt93.cycle3.backend.data;

import java.util.Date;

import javax.validation.constraints.NotNull;

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

    @NotNull(message = "User is required")
    private UserData user;

    @NotNull(message = "Local Team is required")
    private TeamData localTeam;

    @NotNull(message = "Guest Team is Required")
    private TeamData guestTeam;

    @NotNull(message = "Date is required")
    private Date date;

    private int localGoals;

    private int guestGoals;

}
