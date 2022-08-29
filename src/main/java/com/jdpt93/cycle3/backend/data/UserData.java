package com.jdpt93.cycle3.backend.data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

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
public class UserData {

    private int id;

    @NotBlank(message = "Full name is required")
    private String FullName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email is invalid")
    private String email;

    @Pattern(regexp = AuthenticationData.NICKNAME_PATTERN, message = AuthenticationData.NICKNAME_ERROR)
    private String nickname;

    @Pattern(regexp = AuthenticationData.PASSWORD_PATTERN, message = AuthenticationData.PASSWORD_ERROR)
    private String password;

}
