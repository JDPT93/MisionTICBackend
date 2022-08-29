package com.jdpt93.cycle3.backend.services;

import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.jdpt93.cycle3.backend.JwtAuthorizationFilter;
import com.jdpt93.cycle3.backend.converters.UserConverter;
import com.jdpt93.cycle3.backend.data.AuthorizationData;
import com.jdpt93.cycle3.backend.data.AuthenticationData;
import com.jdpt93.cycle3.backend.data.UserData;
import com.jdpt93.cycle3.backend.entities.User;
import com.jdpt93.cycle3.backend.repositories.UserRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private UserConverter userConverter = new UserConverter();

    public AuthorizationData authorize(UserData user, String authorities, int expiration) {
        long currentTimeMillis = System.currentTimeMillis();
        return AuthorizationData.builder()
                .user(user)
                .token(Jwts.builder()
                        .setId(currentTimeMillis + ":" + user.getId())
                        .setSubject(user.getNickname())
                        .claim("authorities", AuthorityUtils.commaSeparatedStringToAuthorityList(authorities)
                                .stream().map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                        .setIssuedAt(new Date(currentTimeMillis))
                        .setExpiration(new Date(currentTimeMillis + expiration))
                        .signWith(SignatureAlgorithm.HS512,
                                JwtAuthorizationFilter.KEY.getBytes())
                        .compact())
                .build();
    }

    public UserData signIn(AuthenticationData authenticationData) {
        Optional<User> optionalUser = userRepository.findByNickname(authenticationData.getNickname());
        if (optionalUser.isEmpty() || !optionalUser.get().checkPassword(authenticationData.getPassword()))
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid authentication data");
        return userConverter.toData(optionalUser.get());
    }

    public UserData signUp(UserData user) {
        user.setId(0);
        if (userRepository.findByEmail(user.getEmail()).isPresent())
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exists");
        if (userRepository.findByNickname(user.getNickname()).isPresent())
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Nickname is already in use");
        return userConverter.toData(userRepository.save(userConverter.toEntity(user)));
    }

}
