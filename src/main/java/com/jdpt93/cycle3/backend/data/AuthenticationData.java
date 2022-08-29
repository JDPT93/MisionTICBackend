package com.jdpt93.cycle3.backend.data;

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
public class AuthenticationData {

    public static final String NICKNAME_PATTERN = "^[A-Za-z][0-9A-Za-z]{0,9}$";
    public static final String NICKNAME_ERROR = "Nickname must start with an alphabetic character and then have alphanumeric characters";

    public static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[@$!%*?&])[0-9A-Za-z@$!%*?&]{8,10}$";
    public static final String PASSWORD_ERROR = "Password must have minimum 8 and maximum 10 characters, at least one uppercase letter, one lowercase letter, one number and one special character";

    @Pattern(regexp = AuthenticationData.NICKNAME_PATTERN, message = AuthenticationData.NICKNAME_ERROR)
    private String nickname;

    @Pattern(regexp = AuthenticationData.PASSWORD_PATTERN, message = AuthenticationData.PASSWORD_ERROR)
    private String password;

}
