package com.jaiwo99.example.workflowdescriber.workflowdescriber;

public class ExecutionFailedException extends RuntimeException {

    public ExecutionFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
