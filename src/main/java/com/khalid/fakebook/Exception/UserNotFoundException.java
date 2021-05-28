package com.khalid.fakebook.Exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String massage) {
        super(massage);
    }

}
