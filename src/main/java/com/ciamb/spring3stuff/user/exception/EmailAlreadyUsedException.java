package com.ciamb.spring3stuff.user.exception;

public class EmailAlreadyUsedException extends Exception {
    private String email;

    public EmailAlreadyUsedException(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Email " + email + " already in use";
    }
}
