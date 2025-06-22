package org.basak.twitterdemo.exception;
public class TwitterDemoException extends RuntimeException {
    private ErrorType errorType;
    public TwitterDemoException(ErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
    }
    public ErrorType getErrorType() {
        return errorType;
    }
}
