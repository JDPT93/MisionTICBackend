package com.jdpt93.cycle3.backend.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jdpt93.cycle3.backend.data.TeamData;
import com.jdpt93.cycle3.backend.services.TeamService;

@RestController
@RequestMapping(path = "/api/teams")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
public class TeamController {

    @Autowired
    private TeamService teamService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(teamService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody TeamData team) {
        return new ResponseEntity<>(teamService.save(team), HttpStatus.OK);
    }

}
