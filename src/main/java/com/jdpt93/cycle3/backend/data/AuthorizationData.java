package com.jdpt93.cycle3.backend.data;

import javax.validation.constraints.NotBlank;
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
public class AuthorizationData {

    @NotNull(message = "User is required")
    private UserData user;

    @NotBlank(message = "Token is required")
    private String token;

}
