package com.bodiukh.exceptions;


public class InvalidAdStateTransitionException extends RuntimeException {
    public InvalidAdStateTransitionException(final String s) {
        super(s);
    }
}
