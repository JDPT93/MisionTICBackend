package com.jdpt93.cycle3.backend.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
@Entity(name = "partidos")
public class Match {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name = "usuario", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private User user;

    @JoinColumn(name = "local", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Team localTeam;

    @JoinColumn(name = "visitante", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Team guestTeam;

    @Column(name = "fecha", nullable = false)
    private Date date;

    @Column(name = "goles_local", nullable = false)
    private int localTeamGoals;

    @Column(name = "goles_visitante", nullable = false)
    private int guestTeamGoals;

}
