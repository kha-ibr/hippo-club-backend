package com.khalid.hippoClub.Exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String massage) {
        super(massage);
    }

}
