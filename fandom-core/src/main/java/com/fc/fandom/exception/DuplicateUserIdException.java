package com.fc.fandom.exception;

public class DuplicateUserIdException extends RuntimeException{

    public DuplicateUserIdException(String message) {
        super(message);
    }
}
