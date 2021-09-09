package com.doeist.SignUp;

import org.springframework.security.core.AuthenticationException;

public class UserAlreadyExistException extends AuthenticationException {
    public UserAlreadyExistException(String msg) {
        super(msg);
    }
}
