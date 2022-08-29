package com.jdpt93.cycle3.backend.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jdpt93.cycle3.backend.data.AuthenticationData;
import com.jdpt93.cycle3.backend.data.UserData;
import com.jdpt93.cycle3.backend.services.UserService;

@Validated
@RestController
@RequestMapping(path = "/api/users")
@CrossOrigin(origins = "*", methods = { RequestMethod.POST })
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "signin")
    public ResponseEntity<?> signIn(@Valid @RequestBody AuthenticationData authenticationData) {
        return new ResponseEntity<>(userService.authorize(userService.signIn(authenticationData), "USER_ROLE", 600000),
                HttpStatus.OK);
    }

    @PostMapping(path = "signup")
    public ResponseEntity<?> signUp(@Valid @RequestBody UserData user) {
        return new ResponseEntity<>(userService.authorize(userService.signUp(user), "USER_ROLE", 600000),
                HttpStatus.CREATED);
    }

}
