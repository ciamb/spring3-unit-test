package com.ciamb.spring3stuff.user.exception;

public class UsernameAlreadyUsedException extends Exception {

    private String username;

    public UsernameAlreadyUsedException(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Username " + username + " already in use";
    }
}
