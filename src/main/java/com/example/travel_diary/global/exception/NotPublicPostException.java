package com.example.travel_diary.global.exception;

public class NotPublicPostException extends IllegalArgumentException{
    public NotPublicPostException() {
        super("This post is not public.");
    }
}
