package com.ciamb.spring3stuff.user.exception;

public class UserNotFoundException extends Exception{
    private Integer id;

    public UserNotFoundException(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "L'utente con id " + id + " non è stato trovato";
    }
}
